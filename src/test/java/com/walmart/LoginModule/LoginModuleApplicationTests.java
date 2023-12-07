package com.walmart.LoginModule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginModuleApplicationTests {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private ObjectMapper objectMapper;

	private String name = "prem14";
	private String email = "prem14@gmail.com";
	private String num = "9843030014";

	@Test
	public void test_ValidCredentials() throws Exception {
		String requestBody = "{\"email\": \"testcase@gmail.com\", \"password\": \"123456\"}";

		mockMvc.perform(post("/api/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk());
				//.andExpect(jsonPath("$.message").value("Login successful !!"));
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

	/*@Test
	public void test_SignupWithValidData() throws Exception {
		String requestBody = "{\"username\": \""+name+"\",\"email\": \""+email+"\",\"phone\": \""+num+"\",\"password\": \"123456\",\"name\": {\"firstName\": \"Arun\",\"lastName\": \"Balaji\"},\"roles\": [\"guest\"],\"gender\": \"male\",\"address\": {\"no\": \"34/0A1\",\"street1\": \"3rd Cross Street\",\"street2\": \"Vellore main road\",\"city\": \"Arcot\",\"pincode\": 632503}}";

		mockMvc.perform(post("/api/auth/signup")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("User registered successfully !!!"));
	}*/

	@Test
	public void test_SignupWithExistingUsername() throws Exception {
		String requestBody = "{\"username\": \"testcase\", \"email\": \"valid_email@gmail.com\", \"phone\": \"1234567890\",\"password\": \"123456\",\"gender\": \"male\"}";

		mockMvc.perform(post("/api/auth/signup")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Error: Username is already taken!"));
	}

	@Test
	public void test_SignupWithExistingEmail() throws Exception {
		String requestBody = "{\"username\": \"valid_user\", \"email\": \"testcase@gmail.com\", \"phone\": \"1234567890\",\"password\": \"123456\",\"gender\": \"male\"}";

		mockMvc.perform(post("/api/auth/signup")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Error: Email is already in use!"));
	}

	@Test
	public void test_SignupWithExistingPhone() throws Exception {
		String requestBody = "{\"username\": \"valid_user\", \"email\": \"valid_email@gmail.com\", \"phone\": \"9943000000\",\"password\": \"123456\",\"gender\": \"male\"}";

		mockMvc.perform(post("/api/auth/signup")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Error: Phone number already in use!"));
	}

	//Test//////////////////////
	@Test
	public void test_SignInAndAccessPage() throws Exception {

		String requestBody = "{\"email\": \"testcase@gmail.com\", \"password\": \"123456\"}";
		// Sign in to get JWT token
		MvcResult signInResult = mockMvc.perform(post("/api/auth/signin")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().exists("Set-Cookie"))
				.andReturn();

		String token = signInResult.getResponse().getCookie("Token").getValue(); //only token

		//2
//		HttpHeaders header = new HttpHeaders();
////		header.add("Cookie","Token="+token);
//		header.add(HttpHeaders.COOKIE,token);
//		header.add(HttpHeaders.CONTENT_TYPE,MediaType.TEXT_PLAIN_VALUE);
//		header.add(HttpHeaders.COOKIE,"Token="+token);

		// Use the obtained token to access a secure page
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/test/role") //role
		mockMvc.perform(get("/api/test/role")
						.header("Cookie", "Token="+token))
//				.contentType(MediaType.APPLICATION_JSON))
//						.headers(header)) //2
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$.message").value("Access granted"));
	}

	@Test
	public void test_AccessPageWithoutToken() throws Exception {
		mockMvc.perform(get("/api/test/guest"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void test_AccessPageWithInvalidToken() throws Exception {
		String requestBody = "{\"email\": \"testcase@gmail.com\", \"password\": \"123456\"}";
		// Sign in to get JWT token
		MvcResult signInResult = mockMvc.perform(post("/api/auth/signin")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().exists("Set-Cookie"))
				.andReturn();

		String token = signInResult.getResponse().getCookie("Token").toString();

		mockMvc.perform(get("/api/test/verify_admin")
						.header("Cookie", token))
				.andExpect(status().isUnauthorized());
	}

	//profile
}

