package footBallProject.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentTeam{
	private Area area;
	private String venue;
	private String website;
	private String address;
	private Contract contract;
	private String tla;
	private int founded;
	private List<RunningCompetitionsItem> runningCompetitions;
	private String clubColors;
	private String name;
	private int id;
	private String shortName;
	private String crest;
}