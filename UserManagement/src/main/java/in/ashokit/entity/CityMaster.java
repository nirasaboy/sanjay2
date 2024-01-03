package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CITY_MASTER")
@Data
public class CityMaster {
	
	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;

}
