package be.virtualsushi.podio.demo.dto.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Rights {

	COMMENT, GRANT, UPDATE, SUBSCRIBE, ADD_TASK, DELETE, VIEW, ADD_CONVERSATION, RATE, ADD_FILE;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator
	public static Rights fromJson(String value) {
		return Rights.valueOf(value.toUpperCase());
	}

}
