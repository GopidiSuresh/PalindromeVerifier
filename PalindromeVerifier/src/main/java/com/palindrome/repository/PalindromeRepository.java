package com.palindrome.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.palindrome.entity.PalindromeEntity;

@Component
public class PalindromeRepository {

	Logger LOGGER = LoggerFactory.getLogger(PalindromeRepository.class);

    public void save(PalindromeEntity entity) {
        writeToFile(entity);
    }

    public PalindromeEntity findByText(String text) {
    	String lineValue = null;
    	try (BufferedReader br = new BufferedReader(new FileReader("palindromes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(text)) {
                    System.out.println("Found: " + line);
                    lineValue = line;
                    break;
                }
            }
        } catch (IOException e) {
        	LOGGER.error("Exception occurs while fetching data from storage for value - {} : {}", text, e.toString());
            e.printStackTrace();
        }
    	
    	if(StringUtils.hasLength(lineValue)) {
    		String values[] = lineValue.split(",");
    		return new PalindromeEntity(values[0], values[1], values[2].equalsIgnoreCase("true") ? true : false);
    	}
        return null;
    }

    private void writeToFile(PalindromeEntity entity) {
        try (FileWriter writer = new FileWriter("palindromes.txt", true)) {
            writer.write(entity.getUsername() + "," + entity.getValue() + "," + entity.isPalindrome() + "\n");
        } catch (IOException e) {
            LOGGER.error("Exception occurs while saving data into storage : {}", e.toString());
        }
    }
}
