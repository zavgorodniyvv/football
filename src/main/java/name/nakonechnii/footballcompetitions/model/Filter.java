package name.nakonechnii.footballcompetitions.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import name.nakonechnii.footballcompetitions.enums.Plan;

@Setter
@Getter
@NoArgsConstructor
//FIXME
@ToString
public class Filter {
    int[] areas;
    Plan plans;
}
