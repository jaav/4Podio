package be.virtualsushi.podio.demo.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthToken implements Serializable {

	private static final long serialVersionUID = 1012409228226057596L;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonProperty("expires_in")
	private int expiresIn;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonIgnore
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private long creationTime;

	public OAuthToken() {
		setCreationTime(System.currentTimeMillis());
	}

	@JsonIgnore
	public long getExpiresAt() {
		return getCreationTime() + expiresIn * 1000;
	}

}
