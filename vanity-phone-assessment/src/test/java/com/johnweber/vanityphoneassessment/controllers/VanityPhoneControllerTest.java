package com.johnweber.vanityphoneassessment.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.johnweber.vanityphoneassessment.engine.VanityPhoneNumberEngine;

@RunWith(MockitoJUnitRunner.class)
public class VanityPhoneControllerTest {
	
	@InjectMocks
	VanityPhoneController controller;

	@Mock
	VanityPhoneNumberEngine phoneNumberGenerator;

	@Test
	public void passesNumberCombinations() {
		controller.getTotalNumberOfVanityNumbers("2145");
		verify(phoneNumberGenerator, times(1)).calculateNumberOfCombinations("2145");
	}

	@Test
	public void getVanityNumbers() {
		doReturn("newValue!").when(phoneNumberGenerator).generateNextPhoneNumber(any());
		doReturn(2).when(phoneNumberGenerator).calculateNumberOfCombinations(any());
		List<String> genNumbers = controller.getNextVanityNumbers("2145", 2);
		verify(phoneNumberGenerator, times(1)).generateNextPhoneNumber("2145");
		verify(phoneNumberGenerator, times(1)).generateNextPhoneNumber("newValue!");
		assertEquals(Arrays.asList("newValue!", "newValue!"), genNumbers);
	}
}
