package be.virtualsushi.podio.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import be.virtualsushi.podio.demo.serialize.DateTimeDeserializer;
import be.virtualsushi.podio.demo.serialize.DateTimeSerializer;
import be.virtualsushi.podio.demo.serialize.LocalDateDeserializer;
import be.virtualsushi.podio.demo.serialize.LocalDateSerializer;
import be.virtualsushi.podio.demo.serialize.LocaleDeserializer;
import be.virtualsushi.podio.demo.serialize.LocaleSerializer;
import be.virtualsushi.podio.demo.serialize.TimeZoneDeserializer;
import be.virtualsushi.podio.demo.serialize.TimeZoneSerializer;
import be.virtualsushi.podio.demo.web.converters.PlainBytesMessageConverter;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RestTemplateSource {

	public static RestTemplate createRestTemplate(ClientHttpRequestInterceptor oauthInterceptor) {
		ObjectMapper objectMapper = new ObjectMapper();

		SimpleModule podioTimeModule = new SimpleModule("direct-enum", new Version(1, 0, 0, "", "be.virtualsushi.direct", "direct"));
		podioTimeModule.addSerializer(new DateTimeSerializer());
		podioTimeModule.addSerializer(new TimeZoneSerializer());
		podioTimeModule.addSerializer(new LocalDateSerializer());
		podioTimeModule.addSerializer(new LocaleSerializer());
		podioTimeModule.addDeserializer(DateTime.class, new DateTimeDeserializer());
		podioTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		podioTimeModule.addDeserializer(TimeZone.class, new TimeZoneDeserializer());
		podioTimeModule.addDeserializer(Locale.class, new LocaleDeserializer());
		objectMapper.registerModule(podioTimeModule);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();

		converters.add(new FormHttpMessageConverter());

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(objectMapper);
		jsonConverter.setPrettyPrint(false);
		converters.add(jsonConverter);

		converters.add(new PlainBytesMessageConverter());

		RestTemplate restTemplate = new RestTemplate(converters);
		restTemplate.getInterceptors().add(oauthInterceptor);
		return restTemplate;
	}

}
