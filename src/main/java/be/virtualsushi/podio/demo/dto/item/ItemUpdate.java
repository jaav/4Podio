package be.virtualsushi.podio.demo.dto.item;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ItemUpdate {

	private List<FieldValuesUpdate> fields;

	private RecurrenceInfo recurrence;

	public ItemUpdate(List<FieldValuesUpdate> fields) {
		this.fields = fields;
	}

}
