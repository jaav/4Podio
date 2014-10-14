package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppFieldConfiguration {

	private AppFieldSettings settings;

}
