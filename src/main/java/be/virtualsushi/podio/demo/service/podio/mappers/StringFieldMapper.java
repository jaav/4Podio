package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Collections;
import java.util.Map;

public class StringFieldMapper extends PodioFieldMapper<String, String> {

	@Override
	protected String doMap(WrappedField fieldWrapper) {
		return fieldWrapper.getFieldValue("[value]", String.class);
	}

	@Override
	public Map<String, String> mapBack(String value) {
		return Collections.singletonMap("value", value);
	}

}
