package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;
import in.ashokit.entity.Email;
import in.ashokit.entity.User;

public interface UserMgmtService {

	public String checkEmail (String email);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates (Integer countryId);
	
	public Map<Integer, String> getCity (Integer stateId);
	
	public String registerUser (UserForm user);
	
	public String unlockAccount (UnlockAccountForm unlockaccForm);
	
	public String login (LoginForm loginForm);
	
	public String forgotPwd (String email);
	
	public boolean sendMail(Email em);
}
