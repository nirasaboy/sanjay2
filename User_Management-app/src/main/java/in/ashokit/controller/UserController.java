package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.service.UserManagementService;

@Controller
public class UserController {
	
	@Autowired
	private UserManagementService service;
	
	@GetMapping("/Email")
	public String checkEmail(@RequestParam(name = "email") String email) {
       
        if (isValidEmail(email)) {
            return "Email is valid.";
        } else {
            return "Email is not valid.";
        }
    

  