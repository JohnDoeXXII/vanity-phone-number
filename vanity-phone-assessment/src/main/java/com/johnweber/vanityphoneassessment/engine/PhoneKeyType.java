package com.johnweber.vanityphoneassessment.engine;

import java.util.Arrays;
import java.util.List;

enum PhoneKeyType {
	ONE('1'),
	TWO('2', 'A', 'B', 'C'),
	THREE('3', 'D', 'E', 'F'),
	FOUR('4', 'G', 'H', 'I'),
	FIVE('5', 'J', 'K', 'L'),
	SIX('6', 'M', 'N', 'O'),
	SEVEN('7', 'P', 'Q', 'R', 'S'),
	EIGHT('8', 'T', 'U', 'V'),
	NINE('9', 'W', 'X', 'Y', 'Z'),
	ASTERISK('*'),
	ZERO('0'),
	POUND('#'),
	;
	
	public static PhoneKeyType fromPart(char phoneNumberPart) throws IllegalArgumentException {
		for (PhoneKeyType phoneKey : values()) {
			if(phoneKey.options.contains(phoneNumberPart)) {
				return phoneKey;
			}
		}
		throw new IllegalArgumentException(String.format("Unrecognized phone number part %c", phoneNumberPart));
	}

	private List<Character> options;
	
	public List<Character> getOptions() {
		return options;
	}

	PhoneKeyType(Character ...phoneNumberPartsForKey) {
		this.options = Arrays.asList(phoneNumberPartsForKey);
	}
}
