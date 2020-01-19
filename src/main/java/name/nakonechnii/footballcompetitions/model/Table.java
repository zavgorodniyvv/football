package name.nakonechnii.footballcompetitions.model;

import lombok.Data;

@Data
public class Table {
    int position;
    Team team;
    int playedGames;
    int won;
    int draw;
    int lost;
    int points;
    int goalsFor;
    int goalsAgainst;
    int goalDifference;
}
