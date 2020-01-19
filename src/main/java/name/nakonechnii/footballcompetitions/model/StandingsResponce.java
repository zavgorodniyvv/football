package name.nakonechnii.footballcompetitions.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class StandingsResponce {
    List<Standings> standings;
}
