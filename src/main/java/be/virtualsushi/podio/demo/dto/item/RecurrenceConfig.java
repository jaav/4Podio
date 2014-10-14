package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class RecurrenceConfig implements Serializable {

	private static final long serialVersionUID = 4338721683699133415L;

	private List<String> days;

	@JsonProperty("repeat_on")
	private String repeatOn;

	public RecurrenceConfig(List<String> days) {
		this.days = days;
	}

	public RecurrenceConfig(String repeatOn) {
		this.repeatOn = repeatOn;
	}

}
