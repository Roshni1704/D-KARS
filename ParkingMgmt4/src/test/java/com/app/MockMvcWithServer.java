package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

//creates a web app context (SC) using any available random free port.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class MockMvcWithServer {
	// randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testTestConroller() throws Exception {

		MvcResult result = mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk()).andReturn();
		assertEquals("Hello, REST !!!!", result.getResponse().getContentAsString());

	}

	@Test
	public void testGetProductByIDPathVar() throws Exception {
		mockMvc.perform(get("/customer/2")).andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.name").value("Ayushi"));
	}

}
