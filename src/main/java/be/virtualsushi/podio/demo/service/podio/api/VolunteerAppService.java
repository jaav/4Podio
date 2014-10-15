package be.virtualsushi.podio.demo.service.podio.api;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import be.virtualsushi.podio.demo.dto.item.Volunteer;
import be.virtualsushi.podio.demo.service.podio.mappers.PodioItemMapper;
import be.virtualsushi.podio.demo.service.podio.mappers.VolunteerItemMapper;

@Service
public class VolunteerAppService extends AbstractAppService<Volunteer> {

	@Inject
	private VolunteerItemMapper mapper;

	@Value("${podio.virtualsushi.volunteer.id}")
	private Integer appId;

	@Override
	protected Integer getAppId() {
		return appId;
	}

	@Override
	protected Class<Volunteer> getItemClass() {
		return Volunteer.class;
	}

	@Override
	protected PodioItemMapper<Volunteer> getMapper() {
		return mapper;
	}

}
