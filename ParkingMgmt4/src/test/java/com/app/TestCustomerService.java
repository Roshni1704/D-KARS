package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.models.User;
import com.app.service.ICustomerService;

@SpringBootTest
class TestCustomerService {
	@Autowired
	private ICustomerService service;

	@Test
	public void testGetCustomerDetails()
	{
		User user=service.getCustomerById(2);
		assertEquals("Lucknow", user.getCity());
	}

}
