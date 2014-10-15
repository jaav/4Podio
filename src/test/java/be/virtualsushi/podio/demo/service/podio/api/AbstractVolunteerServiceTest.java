package be.virtualsushi.podio.demo.service.podio.api;

import javax.inject.Inject;

import be.virtualsushi.podio.demo.dto.FilterRequest;

public class AbstractVolunteerServiceTest extends AbstractApiServiceTest {

	protected VolunteerAppService volunteerAppService;

	@Inject
	public void setVolunteerAppService(VolunteerAppService volunteerAppService) {
		this.volunteerAppService = volunteerAppService;
	}

	protected void list(FilterRequest filter) {
		list(volunteerAppService, filter);
	}

}
