package name.nakonechnii.footballcompetitions.service;

import name.nakonechnii.footballcompetitions.Dto.TableDto;
import name.nakonechnii.footballcompetitions.Exception.NoInformationExeption;
import name.nakonechnii.footballcompetitions.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FootballService {
    HashMap<String, List<TableDto>> tableMap = new HashMap<>();

    public List<TableDto> getCompetitionTable (String county, String name) throws NoInformationExeption {
        int id = 0;
        try {
            id = getCompetitionsNameByCountry(county, name);
        } catch (RestClientException e) {
            if(tableMap.get(county+name) == null) throw new  NoInformationExeption("No connection to information provider and no stored information on your request");
            return tableMap.get(county+name);
        }
        String url = "https://api.football-data.org/v2/competitions/" + id + "/standings?standingType=HOME";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StandingsResponce> response;
        response = restTemplate.exchange(sendRequestToProvider(url), StandingsResponce.class);
        StandingsResponce standingsResponce = response.getBody();
        //FIXME
        List<Table> tables = standingsResponce.getStandings().get(0).getTable();
        List<TableDto> tableDtoList = tables.stream()
                .map(e -> convertTableToTableDto(e))
                .collect(Collectors.toList());
        tableMap.put(county+name, tableDtoList);
        return tableDtoList;

    }
    private TableDto convertTableToTableDto(Table table){
        return TableDto.builder()
                .draw(table.getDraw())
                .lost(table.getLost())
                .name(table.getTeam().getName())
                .points(table.getPoints())
                .position(table.getPosition())
                .won(table.getWon())
                .build();
    }


    public int getCompetitionsNameByCountry(String country, String name) throws RestClientException{
        String uri = "https://api.football-data.org/v2/competitions";
        RestTemplate restTemplate = new RestTemplate();
        sendRequestToProvider(uri);

        ResponseEntity<CompetitionsResponce> response = null;
            response = restTemplate.exchange(sendRequestToProvider(uri), CompetitionsResponce.class);
        CompetitionsResponce competitionsResponce = response.getBody();
        int countries = Arrays.stream(competitionsResponce.getCompetitions())
                .filter(e -> e.getArea().getName().trim().toLowerCase().equals(country.toLowerCase().trim()))
                .filter(e -> e.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
                .map(e -> e.getId())
                .findFirst().orElse(-1);

        return countries;
    }
    private RequestEntity<String> sendRequestToProvider(String uri){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "470adf104bd8432286b70b7e3a2b56b2");
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
        return request;
    }
//    private List<Competition> sendRequestToProvider(String url) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Auth-Token", "470adf104bd8432286b70b7e3a2b56b2");
//        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<CompetitionsResponce> response = restTemplate.exchange(request, CompetitionsResponce.class);
//        CompetitionsResponce competitionsResponce = response.getBody();
//        return Arrays.asList(competitionsResponce.getCompetitions());
//    }
//    public List<Competition> getCompetitionByCountry(int[] id) {
//        String url = "https://api.football-data.org/v2/competitions";
//        if (id != null) {
//            StringBuilder tmp = new StringBuilder(String.valueOf(id[0]));
//            for (int i = 1; i < id.length; i++) {
//                tmp.append(",").append(id[i]);
//            }
//            url = url + "?areas=" + tmp;
//        }
//        return sendRequestToProvider(url);
//    }
}
