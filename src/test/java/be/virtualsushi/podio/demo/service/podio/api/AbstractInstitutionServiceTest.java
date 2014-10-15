package be.virtualsushi.podio.demo.service.podio.api;

import java.util.List;

import javax.inject.Inject;

import be.virtualsushi.podio.demo.dto.FilterRequest;
import be.virtualsushi.podio.demo.dto.item.Institution;

public abstract class AbstractInstitutionServiceTest extends AbstractApiServiceTest {

	protected InstitutionAppService institutionAppService;

	@Inject
	public void setInstitutionAppService(InstitutionAppService institutionAppService) {
		this.institutionAppService = institutionAppService;
	}

	protected List<Institution> list(FilterRequest filter) {
		return list(institutionAppService, filter);
	}

}
