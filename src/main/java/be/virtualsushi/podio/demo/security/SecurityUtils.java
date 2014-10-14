package be.virtualsushi.podio.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

	private SecurityUtils() {
	}

	/**
	 * Get the login of the current user.
	 */
	public static String getCurrentLogin() {
		return getCurrenUser().getUsername();
	}

	public static PodioUserDetails getCurrenUser() {
		try {
			return (PodioUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Check if a user is authenticated.
	 * 
	 * @return true if the user is authenticated, false otherwise
	 */
	public static boolean isAuthenticated() {
		final Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		if (authorities != null) {
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
					return false;
				}
			}
		}

		return true;
	}
}
