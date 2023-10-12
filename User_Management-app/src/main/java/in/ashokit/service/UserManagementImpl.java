package in.ashokit.service;

import java.util.HashMap;
import java.util.Map;

import in.ashokit.binding.UserManagement;

public class UserManagementImpl implements UserManagementService {

	@Override
	public String checkEmail(String email) {
		userManagement  = UserManagement.findByEmail(email);
		
		if(entity == null) {
			return "Invalid Email Id";
			
		}
		return null;
		       
		    }

	
		

	@Override
	public Map<Integer, String> getCountries() {
		
		        // Create a map to store country data
		        Map<Integer, String> countries = new HashMap<>();

		        // Add country data to the map
		        countries.put(1, "United States");
		        countries.put(2, "Canada");
		        countries.put(3, "United Kingdom");
		        countries.put(4, "Australia");
		        countries.put(5, "Germany");

		       
		        return countries;
		    }
		
	




	

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
				  
		
		return null;
	}
		  
		   
		




	@Override
	public Map<Integer, String> getsCities(Integer statesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerUser(UserManagement user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
