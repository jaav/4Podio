package be.virtualsushi.podio.demo.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

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

	@Bean
	public OAuthAppCredentials directAppCredentials() {
		return new OAuthAppCredentials(podioPropertyResolver.getProperty("app.id", Integer.class), podioPropertyResolver.getProperty("app.token"));
	}

	@Bean
	public Module jodaModule() {
		return new JodaModule();
	}

	@Override
	public void setEnvironment(Environment environment) {
		podioPropertyResolver = new RelaxedPropertyResolver(environment, Constants.PODIO_ENV_PREFIX);
	}

}
