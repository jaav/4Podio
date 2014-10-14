package be.virtualsushi.podio.demo.service.podio;

import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import be.virtualsushi.podio.demo.dto.OAuthToken;

import com.podio.oauth.OAuthUserCredentials;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class OAuthService {

	@Inject
	private RestTemplate restTemplate;

	public OAuthToken getToken(OAuthUserCredentials userCredentials) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("grant_type", userCredentials.getType());
		userCredentials.addParameters(parameters);

		MultiValueMap<String, String> request = new LinkedMultiValueMap<>(parameters.size());
		for (String key : parameters.keySet()) {
			request.put(key, parameters.get(key));
		}

		return restTemplate.postForObject("https://api.podio.com/oauth/token", request, OAuthToken.class);
	}

}
