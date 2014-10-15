package be.virtualsushi.podio.demo.service.podio.api;

import org.junit.Test;

public class DeleteInstitutionTest extends AbstractInstitutionServiceTest {

	@Test
	public void testDeleteInstitution() {
		runDelete(institutionAppService);
	}

}
