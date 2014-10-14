package be.virtualsushi.podio.demo.dto.item;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class ItemCreate extends ItemUpdate {

	@JsonProperty("file_ids")
	private List<Integer> fileIds;

	private List<String> tags;

	public ItemCreate() {
		super();
	}

	public ItemCreate(List<FieldValuesUpdate> fields, List<Integer> fileIds, List<String> tags) {
		super(fields);
		this.fileIds = fileIds;
		this.tags = tags;
	}

}
