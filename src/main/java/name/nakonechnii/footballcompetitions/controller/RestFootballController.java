package name.nakonechnii.footballcompetitions.controller;

import name.nakonechnii.footballcompetitions.model.CompetitionsResponce;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RestFootballController {
    @GetMapping(path = "/comp")
    public CompetitionsResponce getCompetition(){
        CompetitionsResponce competitionsResponce = new CompetitionsResponce();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.football-data.org/v2/competitions/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "470adf104bd8432286b70b7e3a2b56b2");
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
        ResponseEntity<CompetitionsResponce> response = restTemplate.exchange(request, CompetitionsResponce.class);
        competitionsResponce = response.getBody();
        return competitionsResponce;
    }
}
