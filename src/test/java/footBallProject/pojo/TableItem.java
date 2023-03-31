package footBallProject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableItem{
	private int goalsFor;
	private String form;
	private int lost;
	private int won;
	private int playedGames;
	private int position;
	private Team team;
	private int draw;
	private int goalsAgainst;
	private int goalDifference;
	private int points;
}