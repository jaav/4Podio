package be.virtualsushi.podio.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Locations {

	DRUGLIJN, HOME;

	@Override
	@JsonValue
	public String toString() {
		return name().toLowerCase();
	}

	@JsonCreator()
	public static Locations getByName(String value) {
		try {
			return Locations.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return Locations.HOME;
		}
	}

}
