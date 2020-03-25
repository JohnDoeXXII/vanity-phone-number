package com.johnweber.vanityphoneassessment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johnweber.vanityphoneassessment.engine.VanityPhoneNumberEngine;

@RestController
@RequestMapping("vanity-number")
public class VanityPhoneController {
	
	@Autowired
	private VanityPhoneNumberEngine phoneNumberGenerator;

	@GetMapping
	public List<String> getNextVanityNumbers(@RequestParam String phone, @RequestParam(required = false) Integer amount) {
		int amountRequested = amount == null ? 1 : amount;
		int maxResultSize = getTotalNumberOfVanityNumbers(phone);
		
		List<String> response = new ArrayList<String>();
		String lastResult = phone;
		while (response.size() < amountRequested && response.size() < maxResultSize) {
			lastResult = phoneNumberGenerator.generateNextPhoneNumber(lastResult);
			response.add(lastResult);
		}
		return response;
	}
	
	@GetMapping("/count")
	public int getTotalNumberOfVanityNumbers(@RequestParam String phone) {
		return phoneNumberGenerator.calculateNumberOfCombinations(phone);
	}

}
