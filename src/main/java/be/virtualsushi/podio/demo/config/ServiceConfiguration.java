package be.virtualsushi.podio.demo.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import be.virtualsushi.podio.demo.service.RestTemplateSource;
import be.virtualsushi.podio.demo.service.podio.SessionTokenInterceptor;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.podio.oauth.OAuthAppCredentials;
import com.podio.oauth.OAuthClientCredentials;

/**
 * Created by spv on 21.03.2014.
 */

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver podioPropertyResolver;

	@Bean
	public OAuthClientCredentials podioClientCredentials() {
		return new OAuthClientCredentials(podioPropertyResolver.getProperty("clientId"), podioPropertyResolver.getProperty("secret"));
	}

	@Bean(name = "institutionCredentials")
	public OAuthAppCredentials institutionAppCredentials() {
		return new OAuthAppCredentials(podioPropertyResolver.getProperty("virtualsushi.institution.id", Integer.class), podioPropertyResolver.getProperty("virtualsushi.institution.token"));
	}

	@Bean(name = "volunteerCredentials")
	public OAuthAppCredentials volunteerAppCredentials() {
		return new OAuthAppCredentials(podioPropertyResolver.getProperty("virtualsushi.volunteer.id", Integer.class), podioPropertyResolver.getProperty("virtualsushi.volunteer.token"));
	}

	@Bean
	public Module jodaModule() {
		return new JodaModule();
	}

	@Override
	public void setEnvironment(Environment environment) {
		podioPropertyResolver = new RelaxedPropertyResolver(environment, Constants.PODIO_ENV_PREFIX);
	}

	@Bean
	public RestTemplate restTemplate(SessionTokenInterceptor oauthHttpRequestInterceptor) {
		return RestTemplateSource.createRestTemplate(oauthHttpRequestInterceptor);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer result = new PropertySourcesPlaceholderConfigurer();
		result.setIgnoreUnresolvablePlaceholders(true);
		return result;
	}

}
