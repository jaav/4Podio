package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRevision {

	private int revision;

	@JsonProperty("app_revision")
	private int appRevision;

	@JsonProperty("created_by")
	private AuthEntity createdBy;

	@JsonProperty("created_on")
	private DateTime createdOn;

}
