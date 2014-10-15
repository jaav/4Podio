package be.virtualsushi.podio.demo.service.podio;

import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.jetty.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.podio.oauth.OAuthClientCredentials;
import com.sun.jersey.core.util.Base64;

@Component
public class SessionTokenInterceptor implements ClientHttpRequestInterceptor {

	@Inject
	private SessionTokenProvider tokenProvider;

	@Inject
	private OAuthClientCredentials oauthClientCredentials;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		if (request.getURI().getPath().contains("oauth")) {
			request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(oauthClientCredentials.getClientId() + ":" + oauthClientCredentials.getClientSecret()), "ASCII"));
		} else {
			request.getHeaders().add(HttpHeaders.AUTHORIZATION, "OAuth2 " + tokenProvider.getToken().getAccessToken());
		}
		return execution.execute(request, body);
	}

}
