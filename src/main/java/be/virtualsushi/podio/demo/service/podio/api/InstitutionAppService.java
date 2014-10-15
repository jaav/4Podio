package be.virtualsushi.podio.demo.service.podio.api;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import be.virtualsushi.podio.demo.dto.item.Institution;
import be.virtualsushi.podio.demo.service.podio.mappers.InstitutionItemMapper;
import be.virtualsushi.podio.demo.service.podio.mappers.PodioItemMapper;

@Service
public class InstitutionAppService extends AbstractAppService<Institution> {

	@Inject
	private InstitutionItemMapper mapper;

	@Value("${podio.virtualsushi.institution.id}")
	private Integer appId;

	@Override
	protected Integer getAppId() {
		return appId;
	}

	@Override
	protected Class<Institution> getItemClass() {
		return Institution.class;
	}

	@Override
	protected PodioItemMapper<Institution> getMapper() {
		return mapper;
	}

}
