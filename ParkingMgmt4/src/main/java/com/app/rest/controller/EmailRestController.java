package com.app.rest.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.app.service.EmailService.sendEmail;

@RestController
@CrossOrigin
public class EmailRestController {//emailrestcontr
	
	//EmailService emailService;
	
	@GetMapping("/sendemail/{to}")
	String sendMail(@PathVariable String to) {
		
		System.out.println("preparing to send message ...");
		String message = "Hello ,your verification code is 1234"; //here booking will generate hashcode will return to frontend and then same will be accessed with path variable
		String subject = "Confirm Parking";//const or sep file
		//String to = "kzee@gmail.com";
		String from = "Parking_Manager";
		sendEmail( message, subject, to, from);
		return "mail sent successfully";
	}
	
	
	
	
	//later this should be changed and only to and subject will be called

}
