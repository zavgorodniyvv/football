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
public class CompetitionsResponce {
    int count;
    Filter filters;
    Competition[] competitions;
}
