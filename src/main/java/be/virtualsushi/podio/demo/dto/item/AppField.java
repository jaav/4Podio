package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppField {

	@JsonProperty("config")
	private AppFieldConfiguration configuration;

}
