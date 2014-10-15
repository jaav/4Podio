package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Institution extends AppItem {

	private static final long serialVersionUID = -5453447908681270502L;

	private String address;

	private String zipcode;

	private String city;

	private String phone;

	private String fax;

	private String email;

	private String website;

	@Override
	public String toString() {
		return "Institution(id=" + getId() + ", title=" + getTitle() + ")";
	}

}
