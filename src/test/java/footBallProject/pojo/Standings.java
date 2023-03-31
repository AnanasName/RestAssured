package footBallProject.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Standings{
	private Area area;
	private Season season;
	private Competition competition;
	private Filters filters;
	private List<StandingsItem> standings;
}