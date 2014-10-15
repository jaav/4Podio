package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Volunteer extends AppItem {

	private static final long serialVersionUID = 8018912268621320804L;

	private String firstName;

	private String lastName;

	private String phone;

	private String email;

	private Institution institution;

	@Override
	public String toString() {
		return "Volunteer(id=" + getId() + ", name=" + getFirstName() + " " + getLastName() + ", institution=" + getInstitution().toString() + ")";
	}
}
