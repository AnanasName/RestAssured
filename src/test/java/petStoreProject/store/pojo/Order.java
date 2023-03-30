package petStoreProject.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order{
	private int petId;
	private int quantity;
	private int id;
	private String shipDate;
	private boolean complete;
	private String status;
}