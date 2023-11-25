package com.walmart.LoginModule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginModuleApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_ValidCredentials() throws Exception {
		String requestBody = "{\"email\": \"arun777@gmail.com\", \"password\": \"123456\"}";

		mockMvc.perform(post("/api/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Login successful !!"));
//				.andExpect(content().string("Login successful !!"));
	}

	@Test
	public void test_InvalidUsername() throws Exception {
		String requestBody = "{\"email\": \"arun777@gmail.co\", \"password\": \"123456\"}";

		mockMvc.perform(post("/api/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message").value("Bad credentials"));
	}

	@Test
	public void test_InvalidPassword() throws Exception {
		String requestBody = "{\"email\": \"arun777@gmail.com\", \"password\": \"12345\"}";

		mockMvc.perform(post("/api/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message").value("Bad credentials"));
	}
}
