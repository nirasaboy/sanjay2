package in.ashokit.binding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class UserManagement {
	
	@Id	
	@Column
    private Integer id;
	private String name;
	private String email;
	private Integer phno;
	private Integer dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	
}
