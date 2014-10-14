package be.virtualsushi.podio.demo.dto.item;

import java.util.List;
import java.util.Map;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldValuesView {

	@JsonProperty("field_id")
	private int id;

	@JsonProperty("external_id")
	private String externalId;

	private ApplicationFieldType type;

	private String label;

	private List<Map<String, ?>> values;

}
