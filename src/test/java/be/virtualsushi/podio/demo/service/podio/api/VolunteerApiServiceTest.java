package be.virtualsushi.podio.demo.service.podio.api;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

public class VolunteerApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private VolunteerAppService volunteerAppService;

	@Test
	public void testGetItem() {
		Object item = volunteerAppService.getItem(204549829);
		Assert.assertNotNull(item);
	}

}
