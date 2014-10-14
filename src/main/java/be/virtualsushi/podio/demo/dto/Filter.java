package be.virtualsushi.podio.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

public abstract class Filter<T> {

	public static interface Converter<T> {

		public String convert(T object);

	}

	protected boolean checkPeriod(Period period, DateTime start, DateTime end) {
		return period == null || (period.contains(start) && period.contains(end));
	}

	protected boolean checkPeriod(Period period, DateTime value) {
		return period == null || period.contains(value);
	}

	protected <V> boolean checkList(List<V> list, V value) {
		return list == null || list.isEmpty() || list.contains(value);
	}

	protected <V> boolean checkNotList(List<V> list, V value) {
		return list == null || list.isEmpty() || !list.contains(value);
	}

	protected <V> boolean checkLists(List<V> filter, List<V> value) {
		if (filter != null && !filter.isEmpty()) {
			if (value == null || value.isEmpty()) {
				return false;
			} else {
				for (V item : filter) {
					if (!value.contains(item)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	protected boolean checkString(String filter, String value) {
		return StringUtils.isBlank(filter) || (StringUtils.isNotBlank(value) && value.toLowerCase().contains(filter.toLowerCase()));
	}

	protected <V> boolean checkObject(V filter, V value) {
		return filter == null || (value != null && value.equals(filter));
	}

	public List<T> filter(List<T> items) {
		List<T> result = new ArrayList<>();
		for (T item : items) {
			if (accept(item)) {
				result.add(item);
			}
		}
		return result;
	}

	public <V> String listToParamString(List<V> list, Converter<V> converter) {
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (V item : list) {
			result.append(converter.convert(item));
			if (list.indexOf(item) != list.size() - 1) {
				result.append(";");
			}
		}
		return result.toString();
	}

	public abstract Map<String, Object> toUrlParametersMap();

	public abstract boolean accept(T item);

}
