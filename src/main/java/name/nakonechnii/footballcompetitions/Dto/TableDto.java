package name.nakonechnii.footballcompetitions.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TableDto {
    int position;
    String name;
    int won;
    int draw;
    int lost;
    int points;
}
