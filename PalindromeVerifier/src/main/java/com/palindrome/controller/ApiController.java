package com.palindrome.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palindrome.entity.RequestModel;
import com.palindrome.entity.ResponseModel;
import com.palindrome.service.PalindromeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/")
public class ApiController {

	 private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@GetMapping("/getResponse")
	public String getResp() {
		
		logger.info("*** api running ***");
		return "success";
	}
	
	
	@Autowired
	private PalindromeService palindromeService;

	@PostMapping("/palindrome")
	public ResponseEntity<ResponseModel> checkPalindrome(@Valid @RequestBody RequestModel request) {
		logger.info("Checking palindrome for value : {}", request.getValue());
		try {
			ResponseModel response = palindromeService.checkPalindrome(request);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			logger.error("Exception occur while checking palindrome : {}", e.toString());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}

