package be.virtualsushi.podio.demo.service.podio.api;

import org.junit.Test;

import be.virtualsushi.podio.demo.dto.FilterRequest;

public class ListInstitutionsTest extends AbstractInstitutionServiceTest {

	@Test
	public void testListInstitutions() {
		list(new FilterRequest());
	}

}
