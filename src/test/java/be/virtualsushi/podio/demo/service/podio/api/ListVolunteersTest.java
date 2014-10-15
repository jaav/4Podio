package be.virtualsushi.podio.demo.service.podio.api;

import org.junit.Test;

import be.virtualsushi.podio.demo.dto.FilterRequest;

public class ListVolunteersTest extends AbstractVolunteerServiceTest {

	@Test
	public void testListVolunteers() {
		list(new FilterRequest());
	}

}
