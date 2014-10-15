package be.virtualsushi.podio.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import be.virtualsushi.podio.demo.dto.OAuthToken;

import com.podio.user.UserStatus;

public class PodioUserDetails implements UserDetails {

	private static final long serialVersionUID = -8768391628061495043L;

	private String username;
	private OAuthToken token;
	private Integer userId;
	private Integer profileId;

	public PodioUserDetails(UserStatus podioUserStatus, OAuthToken token) {
		this.username = podioUserStatus.getUser().getMail();
		this.userId = podioUserStatus.getProfile().getUserId();
		this.profileId = podioUserStatus.getProfile().getProfileId();
		this.token = token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new GrantedAuthority() {

			private static final long serialVersionUID = -3133403436398800587L;

			@Override
			public String getAuthority() {
				return Authorities.ROLE_USER.name();
			}
		});
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public OAuthToken getToken() {
		return token;
	}

	public void setToken(OAuthToken token) {
		this.token = token;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

}
