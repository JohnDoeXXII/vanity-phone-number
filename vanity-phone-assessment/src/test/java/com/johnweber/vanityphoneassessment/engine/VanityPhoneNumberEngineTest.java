package com.johnweber.vanityphoneassessment.engine;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VanityPhoneNumberEngineTest {
	
	@InjectMocks
	VanityPhoneNumberEngine generator;
	
	@Test
	public void promptExample_yayTDD_thanks_for_reading_my_unit_test_name() {
		assertEquals("240386610M", generator.generateNextPhoneNumber("2403866106"));
		assertEquals("240386610N", generator.generateNextPhoneNumber("240386610M"));
		assertEquals("240386610O", generator.generateNextPhoneNumber("240386610N"));
	}
	
	@Test
	public void handlesTheNumeral1() {
		assertEquals("A1", generator.generateNextPhoneNumber("21"));
	}
	
	@Test
	public void handleDoNothing() {
		assertEquals("111111*#", generator.generateNextPhoneNumber("111111*#"));
	}
	
	@Test
	public void allKeyTypesWithRollOver() {
		assertEquals("123456789", generator.generateNextPhoneNumber("1cfilosvz"));
	}
	
	@Test
	public void alwaysUnique() {
		String testNumber = "99911991";
		int numberToGenerate = generator.calculateNumberOfCombinations(testNumber) + 1; // Ensure at least one duplicate if impl correctly
		
		Set<String> generatedNumbers = new HashSet<String>();
		String nextNumber = testNumber;
		for(int i = 0; i < numberToGenerate; i++) {
			nextNumber = generator.generateNextPhoneNumber(nextNumber);
			generatedNumbers.add(nextNumber);
		}

		assertEquals(numberToGenerate - 1, generatedNumbers.size());
	}
}
