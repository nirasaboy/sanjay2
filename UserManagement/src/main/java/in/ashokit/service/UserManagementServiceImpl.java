package in.ashokit.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccountForm;
import in.ashokit.binding.UserForm;
import in.ashokit.entity.CityMaster;
import in.ashokit.entity.CountryMaster;
import in.ashokit.entity.Email;
import in.ashokit.entity.StateMaster;
import in.ashokit.entity.User;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserRepository;
import in.ashokit.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserManagementServiceImpl implements UserMgmtService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired 
	private JavaMailSender send;
	
	@Override
	public String checkEmail(String email) {
		User user = userRepo.findByEmail(email);
		
		if(user== null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMaster> countries = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		countries.forEach(country -> {
			countryMap.put(country.getCountryId(),country.getCountryName());
		});
		return countryMap;
	}
		
		
	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		
		List<StateMaster> states = stateRepo.findByCountryId(countryId);
		
		Map<Integer,String> stateMap = new HashMap<>();
		states.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}
	
	
	@Override
	public Map<Integer, String> getCity(Integer stateId) {
		
		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap<>();
		
		cities.forEach(city -> {
			cityMap.put(city.getCityId(), city.getCityName ());
		});
		return cityMap;
	}
		
	
	@Override
	public String registerUser(UserForm userform) {
		
		//copy data from binding obj to entity obj
		
		User entity = new User();
		BeanUtils.copyProperties(userform, entity);
		
		//generate & set Random pwd
		entity.setUserPwd(generateRandomPwd());
		
		//set Account status as Locked
		entity.setAccStatus("Locked");
		
		userRepo.save(entity);
		
		//send email to unlock account
		
		String to = userform.getEmail();
		String subject = "Registration Email - Unlock Account";
		String body = readEmailBody("REG_Email_body.txt", entity);
				
				emailUtils.sendEmail(to, subject, body);
				
		return "User Account Created";
		
		
	}
	

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		
		String email = unlockAccForm.getEmail();
		
		User user = userRepo.findByEmail(email);
		
		if(user!=null && user.getUserPwd().equals(unlockAccForm.getTempPwd())) {
		user.setUserPwd(unlockAccForm.getNewPwd());
		user.setAccStatus("UNLOCKED");
		userRepo.save(user);
		return "Account Unlocked";
		
		}
		return "Invalid Temporary Password";
	}
	
	

	@Override
	public String login(LoginForm loginForm) {
		
		User user = userRepo .findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPwd());
		
		if(user == null) {
			return "Invalid Credential";
		}
		
		if(user.getAccStatus().equals("Locked")) {
			return "Account Locked";
		}
		return "Success";
	}
		
	
	@Override
	public String forgotPwd(String email) {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			return "No Account Found";
		}
		
		String subject = "Recover Password";
				String body = readEmailBody("FORGOT_PWD_Email_BODY.TXT", user);
				
				emailUtils.sendEmail(email, subject, body);
				
					
					
		// Send email to user with pwd
		
		 
		return  "Password sent to  registered email";
	}
	
	
	private String generateRandomPwd() {
		
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder sb = new StringBuilder();
		
		Random random = new Random();
		
		int pwdLength = 6;
		
		for(int i= 1; i <=pwdLength ; i++) {
			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));
		}
		
		return sb.toString();
	}
		
	
public String readEmailBody(String filename,User user) {
	
	StringBuffer sb = new StringBuffer();
	
	try (Stream<String> lines = Files. lines(Paths.get(filename))){
		
		lines.forEach(line -> {
			
			line = line.replace("${FNAME}", user.getFname());
			line = line.replace("${LNAME}", user.getLname());
			line = line.replace("TEMP_PWD}", user.getUserPwd());
			line = line.replace("${EMAIL}", user.getEmail()); 
			line = line.replace("${PWD}",user.getUserPwd()); 
			
			sb.append(line);
		});
		
	}catch (Exception e) {
		e.printStackTrace();
		
		}
	return sb.toString();
	}
@Override	
public boolean sendMail(Email em) {
	
	boolean isSent = false;
	try {
		MimeMessage mimeMessage = send .createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		
		helper.setFrom("nirsaboy72@gmail.com");
		helper.setTo(em.getTo());
		helper .setSubject(em.getSubject());
		helper.setText("hello");
		send.send(mimeMessage);
		isSent = true;
	} catch(Exception e) {
		e.printStackTrace();
	}
	return isSent;
	}

}