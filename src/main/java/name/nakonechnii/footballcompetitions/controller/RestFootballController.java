package name.nakonechnii.footballcompetitions.controller;

import name.nakonechnii.footballcompetitions.Dto.TableDto;
import name.nakonechnii.footballcompetitions.Exception.NoInformationExeption;
import name.nakonechnii.footballcompetitions.model.Competition;
import name.nakonechnii.footballcompetitions.model.CompetitionsResponce;
import name.nakonechnii.footballcompetitions.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.NoRouteToHostException;
import java.util.List;

@RestController
public class RestFootballController {
    @Autowired
    FootballService footballService;
//    @GetMapping(path = "/comp")
//    public List<Competition> getCompetition(@RequestParam int[] id){
//        return footballService.getCompetitionByCountry(id);
//    }

    @GetMapping(path = "/st/{country}{name}")
    public ResponseEntity getCompetition(@PathVariable String country, @RequestParam String name){
        try {
            return ResponseEntity.ok(footballService.getCompetitionTable(country, name));
        } catch (NoInformationExeption noInformationExeption) {
            return ResponseEntity.status(500).body(noInformationExeption.getMessage());
        }
    }

    @GetMapping(path = "comp/{country}{name}")
    public int getCompetitionsNamesByContry(@PathVariable String country, @RequestParam String name) throws NoRouteToHostException {
        return footballService.getCompetitionsNameByCountry(country, name);
    }

}
