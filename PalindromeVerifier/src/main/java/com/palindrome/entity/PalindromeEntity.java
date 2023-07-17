package com.palindrome.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PalindromeEntity {

	private String username;
	private String value;
	private boolean palindrome;
	
	public PalindromeEntity(String username, String value, boolean palindrome) {
		super();
		this.username = username;
		this.value = value;
		this.palindrome = palindrome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isPalindrome() {
		return palindrome;
	}

	public void setPalindrome(boolean palindrome) {
		this.palindrome = palindrome;
	}
	
	
	
}
