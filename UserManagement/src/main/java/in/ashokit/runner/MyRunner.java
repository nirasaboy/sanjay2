package in.ashokit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.Email;
import in.ashokit.service.UserManagementServiceImpl;

@Component
public class MyRunner implements CommandLineRunner{
	
	@Autowired
	private UserManagementServiceImpl service;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Email em= new Email();
		em.setTo("taraprasad3454@gmail.com");
		em.setSubject("Your Report");
		em.setMessage("hello");
		service.sendMail(em);
		}

}
