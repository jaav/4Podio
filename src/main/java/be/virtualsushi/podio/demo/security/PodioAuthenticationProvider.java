package be.virtualsushi.podio.demo.security;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import be.virtualsushi.podio.demo.service.podio.PodioService;

import com.podio.oauth.OAuthUsernameCredentials;

public class PodioAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private PodioService podioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		OAuthUsernameCredentials userCredentials = new OAuthUsernameCredentials(authentication.getName(), authentication.getCredentials().toString());
		UserDetails user = podioService.doPodioLogin(userCredentials);
		return createSuccessAuthentication(authentication, user);
	}

	protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
