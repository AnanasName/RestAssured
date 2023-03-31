package footBallProject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person{
	private CurrentTeam currentTeam;
	private String firstName;
	private String lastName;
	private String lastUpdated;
	private String nationality;
	private int shirtNumber;
	private String name;
	private String dateOfBirth;
	private int id;
	private String position;
}