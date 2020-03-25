package com.johnweber.vanityphoneassessment.engine;

class PhoneKey {
	
	private PhoneKeyType type;
	private int currentOptionIndex;
	
	public char getValue() {
		return type.getOptions().get(currentOptionIndex);
	}

	public boolean isOutOfOptions() {
		return type.getOptions().size() - 1 == currentOptionIndex;
	}
	
	public void switchToNextOption() {
		currentOptionIndex = isOutOfOptions() ? 0 : currentOptionIndex + 1;
	}
	
	public int getNumberOfOptions() {
		return this.type.getOptions().size();
	}
	
	public PhoneKey(char character) {
		this.type = PhoneKeyType.fromPart(character);
		this.currentOptionIndex = this.type.getOptions().indexOf(character);
	}

}
