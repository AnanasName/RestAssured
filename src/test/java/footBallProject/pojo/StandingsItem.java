package footBallProject.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandingsItem{
	private String stage;
	private String type;
	private List<TableItem> table;
	private Object group;
}