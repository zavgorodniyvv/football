package name.nakonechnii.footballcompetitions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Data
public class Team {
    int id;
    Area area;
    String name;
    String shortName;
    String tla;
    String crestUrl;
    String address;
    String phone;
    String website;
    String email;
    int founded;
    String clubColors;
    String venue;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime lastUpdated;
}
