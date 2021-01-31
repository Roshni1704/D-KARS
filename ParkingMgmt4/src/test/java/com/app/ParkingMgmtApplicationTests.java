package com.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.rest.controller.TestController;

@SpringBootTest
class ParkingMgmtApplicationTests {

	@Autowired
	private TestController controller;

	@Test
	void contextLoads() {
		System.out.println("in test");
		assertNotNull(controller);
	}

}
