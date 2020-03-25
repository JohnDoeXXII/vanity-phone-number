package com.johnweber.vanityphoneassessment.engine;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class VanityPhoneNumberEngine {

	public String generateNextPhoneNumber(String phoneNumber) {
		List<PhoneKey> phoneKeys = phoneNumberAsKeys(phoneNumber);
		
		pushToNextKeyOptionForIndex(phoneKeys, phoneKeys.size() - 1);
		
		StringBuilder phoneKeysAsNumber = new StringBuilder();
		for (PhoneKey phoneKey : phoneKeys) {
			phoneKeysAsNumber.append(phoneKey.getValue());
		}
		return phoneKeysAsNumber.toString();
	}
	
	public int calculateNumberOfCombinations(String phoneNumber) {
		List<PhoneKey> keys = phoneNumberAsKeys(phoneNumber);
		
		return keys.stream().map(PhoneKey::getNumberOfOptions).reduce(1, (a, b) -> b * a);
	}
	
	private void pushToNextKeyOptionForIndex(List<PhoneKey> baseNumber, int thisIndex) {
		boolean shouldGoToNextKey = baseNumber.get(thisIndex).isOutOfOptions();
		baseNumber.get(thisIndex).switchToNextOption();
		
		int nextIndex = thisIndex - 1;
		
		if(shouldGoToNextKey && nextIndex >= 0) {
			pushToNextKeyOptionForIndex(baseNumber, nextIndex);
		}
	}
	
	private List<PhoneKey> phoneNumberAsKeys(String number) {
		return number.toUpperCase().chars().mapToObj(i -> (char) i)
				.map(PhoneKey::new)
				.collect(Collectors.toList());
	}
}
