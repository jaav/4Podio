package be.virtualsushi.podio.demo.service.podio;

import be.virtualsushi.podio.demo.dto.OAuthToken;

import com.podio.oauth.OAuthRefreshTokenCredentials;

public class LocalTokenProvider implements OAuthTokenProvider {

	private OAuthToken token;

	private final OAuthService oauthService;

	public LocalTokenProvider(OAuthService oauthService, OAuthToken token) {
		this.token = token;
		this.oauthService = oauthService;
	}

	@Override
	public OAuthToken getToken() {
		if (token.getExpiresAt() < System.currentTimeMillis() - 30) {
			token = oauthService.getToken(new OAuthRefreshTokenCredentials(token.getRefreshToken()));
		}
		return token;
	}

}
