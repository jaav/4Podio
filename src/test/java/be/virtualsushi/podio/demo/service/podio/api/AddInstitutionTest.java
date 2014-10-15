package be.virtualsushi.podio.demo.service.podio.api;

import org.junit.Test;

import be.virtualsushi.podio.demo.dto.item.Institution;

public class AddInstitutionTest extends AbstractInstitutionServiceTest {

	public static final String[] WORDS = { "Fancy ", "Amazing ", "Terrific ", "Gorgeous ", "Sensational ", "Fantastic ", "Fabulous ", "Magnificent ", "Stunning ", "Marvelous " };

	@Test
	public void testAppInstitution() {
		Institution institution = new Institution();
		institution.setTitle(WORDS[randomIndex()] + WORDS[randomIndex()] + "Institution");
		institution.setCity("Bangkok");
		institution.setPhone("88002000600");
		institution.setFax("88002000600");
		institution.setWebsite("http://google.com");
		institution.setEmail("some@gmail.com");
		institution.setZipcode("1234");
		Institution result = institutionAppService.addItem(institution, true);
		System.out.println("Added institution: " + result);
	}
}
