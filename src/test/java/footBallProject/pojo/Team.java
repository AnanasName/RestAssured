package footBallProject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team{
	private String name;
	private String tla;
	private int id;
	private String shortName;
	private String crest;
}