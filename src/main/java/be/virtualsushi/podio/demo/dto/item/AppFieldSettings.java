package be.virtualsushi.podio.demo.dto.item;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppFieldSettings {

	private List<CategoryOption> options;

}
