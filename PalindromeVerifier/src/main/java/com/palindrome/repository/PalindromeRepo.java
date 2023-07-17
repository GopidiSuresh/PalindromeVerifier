package com.palindrome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palindrome.entity.PalindromePersist;

@Repository
public interface PalindromeRepo extends JpaRepository<PalindromePersist, Integer>{

	
}
