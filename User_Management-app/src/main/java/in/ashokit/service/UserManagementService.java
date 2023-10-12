package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.UserManagement;

public interface UserManagementService {

	public String checkEmail(String email);
	
	public Map<Integer,String> getCountries();
	
	public  Map<Integer,String> getStates(Integer countryId);
		
	public Map<Integer,String> getsCities(Integer statesId);
	
	public String registerUser(UserManagement user);
	
	
	public String forgotPwd(String email);
}
