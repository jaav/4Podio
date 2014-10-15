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
	private static final String[] DESCRIPTIONS = { "Software Developer", "Manager", "Boss", "Nice guy", "Designer", "Security", "HR" };

	@Inject
	private InstitutionAppService institutionAppService;

	@Test
	public void testAddVolunteer() {
		Volunteer volunteer = new Volunteer();
		String name = NAMES[randomIndex(NAMES.length)];
		String surname = SURNAMES[randomIndex(SURNAMES.length)];
		volunteer.setFirstName(name);
		volunteer.setLastName(surname);
		volunteer.setPhone("+1100" + randomDecIndex() + "00" + randomDecIndex() + "00" + randomDecIndex());
		volunteer.setEmail(name.toLowerCase() + "." + surname.toLowerCase() + "@gmail.com");
		volunteer.setDescription(DESCRIPTIONS[randomIndex(DESCRIPTIONS.length)]);
		List<Institution> institutions = list(institutionAppService, new FilterRequest());
		volunteer.setInstitution(institutions.get(randomIndex(institutions.size())));
		Volunteer result = volunteerAppService.addItem(volunteer, true);
		System.out.println("Added volunteer: " + result);
	}

}
