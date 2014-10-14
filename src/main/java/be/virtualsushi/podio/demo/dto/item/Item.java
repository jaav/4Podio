package be.virtualsushi.podio.demo.dto.item;

import java.util.List;

import lombok.Data;
import be.virtualsushi.podio.demo.dto.file.FileLink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	@JsonProperty("item_id")
	private int id;

	@JsonProperty("external_id")
	private String externalId;

	@JsonProperty("initial_revision")
	private ItemRevision initialRevision;

	private String title;

	private List<FieldValuesView> fields;

	private List<FileLink> files;

	private List<String> tags;

	private List<Rights> rights;

	private RecurrenceInfo recurrence;

}
