package be.virtualsushi.podio.demo.service.podio;

import java.io.IOException;

import org.eclipse.jetty.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.podio.oauth.OAuthClientCredentials;
import com.sun.jersey.core.util.Base64;

public class ClientCredentialsInterceptor implements ClientHttpRequestInterceptor {

	private OAuthClientCredentials oauthClientCredentials;

	public ClientCredentialsInterceptor(OAuthClientCredentials oauthClientCredentials) {
		this.oauthClientCredentials = oauthClientCredentials;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(oauthClientCredentials.getClientId() + ":" + oauthClientCredentials.getClientSecret()), "ASCII"));
		return execution.execute(request, body);
	}

}
