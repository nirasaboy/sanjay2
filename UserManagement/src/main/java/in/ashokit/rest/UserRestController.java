package in.ashokit.rest;

import java.util.Map;
import in.ashokit.utils.EmailUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;
import in.ashokit.entity.Email;
import in.ashokit.service.UserMgmtService;

@RestController
public class UserRestController {

	@Autowired
	private UserMgmtService userMgmtService;
	
	@Autowired
	private EmailUtils email;
	
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody LoginForm loginForm){
		String status = userMgmtService.login(loginForm);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@GetMapping("/countries")
	public Map<Integer,String> loadCountries(){
		return userMgmtService.getCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> loadStates(@PathVariable Integer countryId){
		return userMgmtService.getStates(countryId);
	}
	
	
	@GetMapping("/city/{stateId}")
	public Map<Integer,String> loadCities(@PathVariable Integer stateId){
		return userMgmtService.getCity(stateId);
	}
	
	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable String email) {
		return userMgmtService.checkEmail(email);
	}
	@PostMapping("/user")
	public ResponseEntity<String> userRegistration(@RequestBody UserForm userForm){
		String status = userMgmtService.registerUser(userForm);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/unlock")
	public ResponseEntity<String> unlockAccount(@RequestBody UnlockAccountForm unlockAccForm){
		String status = userMgmtService.unlockAccount(unlockAccForm);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email){
		String status = userMgmtService.forgotPwd(email);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	}
	

