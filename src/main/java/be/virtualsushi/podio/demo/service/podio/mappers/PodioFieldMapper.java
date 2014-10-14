package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import be.virtualsushi.podio.demo.dto.item.FieldValuesView;

public abstract class PodioFieldMapper<T, V> {

	public static class WrappedField {

		private final BeanWrapper wrapped;

		public WrappedField(FieldValuesView field) {
			wrapped = PropertyAccessorFactory.forBeanPropertyAccess(field);
		}

		@SuppressWarnings("unchecked")
		protected <V> V getFieldValue(String path, Class<V> resultClass) {
			Object value = null;
			try {
				value = wrapped.getPropertyValue("values[0]" + path);
			} catch (Exception e) {
				return null;
			}
			return value != null ? (V) value : null;
		}

	}

	public T map(FieldValuesView field) {
		return doMap(new WrappedField(field));
	}

	public abstract Map<String, V> mapBack(T value);

	protected abstract T doMap(WrappedField fieldWrapper);

}
