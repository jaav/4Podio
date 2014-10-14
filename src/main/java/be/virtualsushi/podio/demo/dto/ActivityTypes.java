package be.virtualsushi.podio.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityTypes {

	CHAT, SKYPE, PHONE, EMAIL, NONE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static ActivityTypes getByName(String value) {
		return ActivityTypes.valueOf(value.toUpperCase());
	}

}
