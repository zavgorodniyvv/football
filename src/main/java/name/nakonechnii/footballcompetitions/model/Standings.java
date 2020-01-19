package name.nakonechnii.footballcompetitions.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Standings {
    String stage;
    String type;
    String group;
    List<Table> table;
}
