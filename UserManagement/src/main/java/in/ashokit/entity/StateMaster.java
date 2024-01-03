package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "STATE_MASTER")
@Data
public class StateMaster {
	
	@Id
	private Integer stateId;
	private String stateName;
	private Integer countryId;

}
