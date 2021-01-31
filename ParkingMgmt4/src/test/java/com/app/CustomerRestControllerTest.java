package com.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.app.dao.models.Role;
import com.app.dao.models.User;
import com.app.rest.controller.CustomerRestController;
import com.app.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CustomerRestController.class)
public class CustomerRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRestController customerRestController;

	@MockBean
	private ICustomerService customerService;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void smokeTest() {
		assertNotNull(customerRestController);
	}

	@Test
	public void getCustomerById() throws Exception {

		User user = new User("abc", "abc@gmail.com", "abc123", "12345", "mp", "indore", "abc", LocalDate.now(),
				Role.CUSTOMER);

		when(customerService.getCustomerById(12)).thenReturn(user);
		mockMvc.perform(get("/customer/12")).andExpect(jsonPath("$.name").value("abc")).andExpect(status().isOk());

	}

	public String jsonString(Object obj) throws Exception {

		return mapper.writeValueAsString(obj);

	}
}
