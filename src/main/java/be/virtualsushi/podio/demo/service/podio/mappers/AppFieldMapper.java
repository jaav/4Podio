package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Collections;
import java.util.Map;

import be.virtualsushi.podio.demo.dto.item.AppItem;

public class AppFieldMapper<T extends AppItem> extends PodioFieldMapper<T, Integer> {

	private Class<T> resultClass;

	public AppFieldMapper(Class<T> resultClass) {
		this.resultClass = resultClass;
	}

	@Override
	public Map<String, Integer> mapBack(T value) {
		return Collections.singletonMap("value", value.getId());
	}

	@Override
	protected T doMap(WrappedField fieldWrapper) {
		try {
			T result = resultClass.newInstance();
			result.setTitle(fieldWrapper.getFieldValue("[value][title]", String.class));
			result.setId(fieldWrapper.getFieldValue("[value][item_id]", Integer.class));
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
