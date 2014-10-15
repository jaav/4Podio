package be.virtualsushi.podio.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.virtualsushi.podio.demo.service.podio.mappers.InstitutionItemMapper;
import be.virtualsushi.podio.demo.service.podio.mappers.VolunteerItemMapper;

@Configuration
public class MappersConfiguration {

	@Bean
	public InstitutionItemMapper institutionItemMapper() {
		return new InstitutionItemMapper();
	}

	@Bean
	public VolunteerItemMapper volunteerItemMapper() {
		return new VolunteerItemMapper();
	}

}
