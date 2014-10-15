package be.virtualsushi.podio.demo.service.podio.api;

import org.junit.Test;

public class DeleteVolunteerTest extends AbstractVolunteerServiceTest {

	@Test
	public void testDeleteVolunteer() {
		runDelete(volunteerAppService);
	}

}
