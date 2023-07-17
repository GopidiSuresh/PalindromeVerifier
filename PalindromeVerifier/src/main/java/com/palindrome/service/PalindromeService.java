package com.palindrome.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.palindrome.entity.PalindromeEntity;
import com.palindrome.entity.PalindromePersist;
import com.palindrome.entity.RequestModel;
import com.palindrome.entity.ResponseModel;
import com.palindrome.repository.PalindromeRepo;
import com.palindrome.repository.PalindromeRepository;

@Service
public class PalindromeService {

	Logger LOGGER = LoggerFactory.getLogger(PalindromeService.class);

	@Autowired
	private PalindromeRepository palindromeRepository;
	
	@Autowired
	private PalindromeRepo palindromeRepo;
	
	@Autowired
    CacheManager cacheManager;

	@Cacheable(value = "palindromes")
	public ResponseModel checkPalindrome(RequestModel request) {
		PalindromeEntity entity = palindromeRepository.findByText(request.getUsername() + "," + request.getValue());
		if (entity != null) {
			LOGGER.info("Found record in db for value : {}", request.getValue());
			return new ResponseModel(entity.getUsername(), entity.getValue(), entity.isPalindrome());
		}

		String value = request.getValue().toLowerCase();

		boolean palindrome = new StringBuilder(value).reverse().toString().equals(value);
		LOGGER.info("Palindrome status for value : {} is : {}", request.getValue(), palindrome);

		entity = new PalindromeEntity(request.getUsername(), request.getValue(), palindrome);
		palindromeRepository.save(entity);
		
		//saving to DB
		PalindromePersist palindromePersist = new PalindromePersist(request.getUsername(), request.getValue(), palindrome);
		palindromeRepo.saveAndFlush(palindromePersist);

		return new ResponseModel(request.getUsername(), request.getValue(), palindrome);
	}
	
	@PostConstruct
	public void init() {
		update();

	}

	public void update() {
		for (PalindromePersist palindromePersist : palindromeRepo.findAll()) {
			cacheManager.getCache("palindromes").put("palindromes", palindromePersist);
		}
	}
}
