package name.nakonechnii.footballcompetitions.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
//FIXME
@ToString
public class Winner {
    int id;
    String name;
    String shortName;
    String tla;
    String crestUrl;
}
