package be.virtualsushi.podio.demo.dto.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecurrenceTypes {

	DAILY, WEEKLY, MONTHLY, YEARLY;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static RecurrenceTypes fromJson(String value) {
		return RecurrenceTypes.valueOf(value.toUpperCase());
	}

}
