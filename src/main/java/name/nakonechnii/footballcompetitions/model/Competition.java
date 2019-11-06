package name.nakonechnii.footballcompetitions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import name.nakonechnii.footballcompetitions.enums.Plan;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
//FIXME
@ToString
public class Competition {
    int id;
    Area area;
    String name;
    String code;
    String emblemUrl;
    Plan plan;
    CurrentSeason currentSeason;
    int numberOfAvailableSeasons;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss Z")
    LocalDateTime lastUpdated;
}
