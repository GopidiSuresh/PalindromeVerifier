package com.palindrome.PalindromeVerifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palindrome.entity.RequestModel;
import com.palindrome.entity.ResponseModel;
import com.palindrome.repository.PalindromeRepository;
import com.palindrome.service.PalindromeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
class PalindromeVerifierApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PalindromeService palindromeService;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testCheckPalindrome_ValidInput_ReturnsTrue() throws Exception {
		RequestModel request = new RequestModel();
		request.setUsername("user1");
		request.setValue("madam");

		ResponseModel response = new ResponseModel();
		response.setUsername(request.getUsername());
		response.setValue(request.getValue());
		response.setPalindrome(true);

		Mockito.when(palindromeService.checkPalindrome(Mockito.any(RequestModel.class)))				.thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.post("/palindrome")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(request.getUsername()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value").value(request.getValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.palindrome").value(true));
	}
}
