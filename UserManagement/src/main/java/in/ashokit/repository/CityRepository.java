package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CityMaster;

public interface CityRepository extends JpaRepository<CityMaster,Serializable> {
	
	//select * from city_master where state_id=?
	public List<CityMaster> findByStateId(Integer stateId);

}
