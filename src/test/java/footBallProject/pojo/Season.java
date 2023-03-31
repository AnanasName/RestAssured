package footBallProject.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season{
	private Object winner;
	private int currentMatchday;
	private String endDate;
	private List<String> stages;
	private int id;
	private String startDate;
}