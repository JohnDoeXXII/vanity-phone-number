package com.johnweber.vanityphoneassessment.engine;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;

public class PhoneKeyTest {
	
	@Test(expected = Test.None.class)
	public void createsPhoneKeyFromValues_withoutException() {
		String stringOValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890#*";
		stringOValidChars.chars()
		  .mapToObj(charInt -> (char) charInt)
		  .map(PhoneKey::new)
		  .collect(Collectors.toList());
	}
	
	@Test()
	public void errorsConstructorForInvalidChar() {
		String stringOInvalidChars = "!@$%^&()-{}=+.";
		stringOInvalidChars.chars()
		  .mapToObj(charInt -> (char) charInt)
		  .map(character -> {
			  PhoneKey key = null;
			  try {
				  key = new PhoneKey(character);
				  fail("No exception throw");
			  } catch (IllegalArgumentException e) {
				  // nice! 
			  }
			  return key;
		  })
		  .collect(Collectors.toList());
	}
	
	@Test
	public void switchesToNextOption() {
		PhoneKey key = new PhoneKey('2');
		key.switchToNextOption();
		assertEquals('A', key.getValue());
	}
	
	@Test
	public void switchesToNextOptionRollover() {
		PhoneKey key = new PhoneKey('C');
		key.switchToNextOption();
		assertEquals('2', key.getValue());
	}
	
	@Test
	public void switchesToNextOptionAlthoughThereIsNone() {
		PhoneKey key = new PhoneKey('1');
		key.switchToNextOption();
		assertEquals('1', key.getValue());
	}
	
	@Test
	public void isOutOfOptionsStandard() {
		PhoneKey key = new PhoneKey('9');
		assertEquals(false, key.isOutOfOptions());
	}
	
	@Test
	public void isOutOfOptionsWhenThereAreNone() {
		PhoneKey key = new PhoneKey('1');
		assertEquals(true, key.isOutOfOptions());
	}
	
	@Test
	public void isOutOfOptionsOnRollover() {
		PhoneKey key = new PhoneKey('C');
		assertEquals(true, key.isOutOfOptions());
	}

}
