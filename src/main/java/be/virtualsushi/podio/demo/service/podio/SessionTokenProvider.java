package be.virtualsushi.podio.demo.service.podio;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import be.virtualsushi.podio.demo.dto.OAuthToken;
import be.virtualsushi.podio.demo.security.PodioUserDetails;
import be.virtualsushi.podio.demo.security.SecurityUtils;

import com.podio.oauth.OAuthRefreshTokenCredentials;

@Component
public class SessionTokenProvider implements OAuthTokenProvider {

	@Inject
	private OAuthService oauthService;

	private Object lock = new Object();

	@Override
	public OAuthToken getToken() {
		PodioUserDetails userDetails = SecurityUtils.getCurrenUser();

		synchronized (lock) {
			if (userDetails.getToken().getExpiresAt() < System.currentTimeMillis() - 30) {
				OAuthToken token = oauthService.getToken(new OAuthRefreshTokenCredentials(userDetails.getToken().getRefreshToken()));
				userDetails.setToken(token);
			}
			return userDetails.getToken();
		}
	}

}
