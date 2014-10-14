package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Collections;
import java.util.Map;

import be.virtualsushi.podio.demo.dto.Profile;

public class ContactFieldMapper extends PodioFieldMapper<Profile, Integer> {

	@Override
	protected Profile doMap(WrappedField fieldWrapper) {
		Profile result = new Profile();
		result.setUserId(fieldWrapper.getFieldValue("[value][user_id]", Integer.class));
		result.setProfileId(fieldWrapper.getFieldValue("[value][profile_id]", Integer.class));
		result.setName(fieldWrapper.getFieldValue("[value][name]", String.class));
		result.setAvatarUrl(fieldWrapper.getFieldValue("[value][image][link]", String.class));
		return result;
	}

	@Override
	public Map<String, Integer> mapBack(Profile value) {
		return Collections.singletonMap("value", value.getProfileId());
	}

}
