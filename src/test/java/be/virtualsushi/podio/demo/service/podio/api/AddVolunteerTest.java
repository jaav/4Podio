package be.virtualsushi.podio.demo.service.podio.api;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import be.virtualsushi.podio.demo.dto.FilterRequest;
import be.virtualsushi.podio.demo.dto.item.Institution;
import be.virtualsushi.podio.demo.dto.item.Volunteer;

public class AddVolunteerTest extends AbstractVolunteerServiceTest {

	private static final String[] NAMES = { "Martin", "Neo", "Paul", "Joe", "Phiby", "Monica", "Rachel", "Ross", "Albert", "Chandler" };
	private static final String[] SURNAMES = { "Fry", "Anderson", "Smith", "Tribiani", "Buffe", "Geller", "Green", "Bink", "Einstein", "Lorry" };

	@Inject
	private InstitutionAppService institutionAppService;

	@Test
	public void testAddVolunteer() {
		Volunteer volunteer = new Volunteer();
		String name = NAMES[randomIndex()];
		String surname = SURNAMES[randomIndex()];
		volunteer.setFirstName(name);
		volunteer.setLastName(surname);
		volunteer.setPhone("+1100" + randomIndex() + "00" + randomIndex() + "00" + randomIndex());
		volunteer.setEmail(name.toLowerCase() + "." + surname.toLowerCase() + "@gmail.com");
		List<Institution> institutions = list(institutionAppService, new FilterRequest());
		Integer number = readConsoleInt("Enter institution numder");
		volunteer.setInstitution(institutions.get(number - 1));
		Volunteer result = volunteerAppService.addItem(volunteer, true);
		System.out.println("Added volunteer: " + result);
	}

}
