package be.virtualsushi.podio.demo.service.podio;

import java.io.IOException;

import org.eclipse.jetty.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import be.virtualsushi.podio.demo.dto.OAuthToken;

public class LocalTokenInterceptor implements ClientHttpRequestInterceptor {

	private final OAuthToken token;

	public LocalTokenInterceptor(OAuthToken oAuthToken) {
		this.token = oAuthToken;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().add(HttpHeaders.AUTHORIZATION, "OAuth2 " + token.getAccessToken());
		return execution.execute(request, body);
	}

}
