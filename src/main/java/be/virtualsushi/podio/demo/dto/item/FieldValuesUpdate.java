package be.virtualsushi.podio.demo.dto.item;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValuesUpdate {

	@JsonProperty("field_id")
	private Integer id;

	private List<Map<String, ?>> values;

	public FieldValuesUpdate(int id, Map<String, ?> value) {
		super();
		this.id = id;
		this.values = Collections.<Map<String, ?>> singletonList(value);
	}

}
