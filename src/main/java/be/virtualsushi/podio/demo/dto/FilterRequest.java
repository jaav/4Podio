package be.virtualsushi.podio.demo.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest implements Serializable {

	private static final long serialVersionUID = 3765029061142605370L;

	@JsonProperty("sort_by")
	private String sortBy;

	@JsonProperty("soret_desc")
	private boolean sortDesc;

	private Integer limit = 30;

	private Integer offset = 0;

	private boolean remember;

	private Map<String, Object> filters;

	public FilterRequest addFilter(Integer key, Object value) {
		return addFilter(String.valueOf(key), value);
	}

	public FilterRequest addFilter(String key, Object value) {
		if (filters == null) {
			filters = new HashMap<>();
		}
		filters.put(key, value);
		return this;
	}

	public Object getFilter(Integer key) {
		return getFilter(String.valueOf(key));
	}

	public Object getFilter(String key) {
		if (filters == null)
			return null;
		return filters.get(key);
	}
}
