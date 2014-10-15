package be.virtualsushi.podio.demo.dto;

import lombok.Data;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpaceMembershipInfo {

	private Profile profile;

	@JsonProperty("started_on")
	private DateTime startedOn;

	@JsonProperty("invited_on")
	private DateTime invitedOn;

	private String role;

	@JsonProperty("ended_on")
	private DateTime endedOn;

}
