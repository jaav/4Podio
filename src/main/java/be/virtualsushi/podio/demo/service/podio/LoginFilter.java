package be.virtualsushi.podio.demo.service.podio;

import org.eclipse.jetty.http.HttpHeaders;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;

public class LoginFilter extends com.podio.LoginFilter {

	private final OAuthTokenProvider authProvider;

	public LoginFilter(OAuthTokenProvider authProvider) {
		super(null);
		this.authProvider = authProvider;
	}

	@Override
	public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
		cr.getHeaders().add(HttpHeaders.AUTHORIZATION, "OAuth2 " + authProvider.getToken().getAccessToken());
		return getNext().handle(cr);
	}
}
