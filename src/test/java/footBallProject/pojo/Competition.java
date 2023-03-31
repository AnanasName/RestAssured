package footBallProject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competition{
	private String code;
	private String name;
	private int id;
	private String type;
	private String emblem;
}