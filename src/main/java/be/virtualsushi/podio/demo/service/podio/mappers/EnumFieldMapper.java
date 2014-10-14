package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Collections;
import java.util.Map;

public class EnumFieldMapper<T extends Enum<T>> extends PodioFieldMapper<T, Integer> {

	private T[] values;

	@SuppressWarnings("unchecked")
	public EnumFieldMapper(T... values) {
		this.values = values;
	}

	@Override
	public Map<String, Integer> mapBack(T value) {
		return Collections.singletonMap("value", value.ordinal() + 1);
	}

	@Override
	protected T doMap(WrappedField fieldWrapper) {
		return values[fieldWrapper.getFieldValue("[value][id]", Integer.class) - 1];
	}
}
