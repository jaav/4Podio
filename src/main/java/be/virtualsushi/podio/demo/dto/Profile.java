package be.virtualsushi.podio.demo.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "userId", "profileId" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Profile implements Serializable {

	private static final long serialVersionUID = -10317534441138480L;

	private String name;

	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("profile_id")
	private Integer profileId;

	private Image image;

	private List<String> address;

	private List<Locations> location;

	private List<String> phone;

	private List<String> mail;

	private List<String> title;

	private List<ActivityTypes> skill;

	private String about;

	private String linkedin;

	private String twitter;

	private String skype;

	private String city;

	private String zip;

	private String state;

	private String country;

	private Integer avatar;

	@JsonProperty("last_seen_on")
	private DateTime lastSeenOn;

	public Profile(Integer userId, Integer profileId) {
		this(userId, profileId, null);
	}

	public Profile(Integer userId, Integer profileId, String name) {
		this.userId = userId;
		this.profileId = profileId;
		this.name = name;
	}

	public Profile(Integer userId, String name) {
		this(userId, null, name);
	}

	@JsonProperty
	public String getAvatarUrl() {
		if (image != null) {
			return image.getLink();
		}
		return null;
	}

	@JsonProperty
	public Boolean isActive() {
		if (lastSeenOn == null) {
			return null;
		}
		return lastSeenOn.isAfter(new DateTime().minusMinutes(10));
	}

	public void setAvatarUrl(String link) {
		this.image = new Image(link);
	}

}
