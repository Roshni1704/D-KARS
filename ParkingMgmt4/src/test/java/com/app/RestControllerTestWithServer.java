package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.app.dao.models.User;


//Integration Test : complete end to end testing
//creates a web app context (SC) using any available random free port.

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestControllerTestWithServer {
	
	//randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;
	
	// Abstraction of REST client to be used in test scenario
	@Autowired
	private TestRestTemplate template;

	@Test
	public void testTestConroller() throws Exception {

		String response = template.getForObject
				("http://localhost:" + serverPort + "/test", String.class);
		assertEquals("Hello, REST !!!!",response);
	}
	@Test
	public void testCustomerRestConrollerGetCustomerByIDPathVar()  {

		User user = template.getForObject("http://localhost:" + serverPort + "/customer/2", User.class);
		assertEquals("Ayushi", user.getName());
	}
	
}
