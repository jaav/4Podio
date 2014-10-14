package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import be.virtualsushi.podio.demo.dto.Period;
import be.virtualsushi.podio.demo.serialize.DateTimeUtil;

public class DateFieldMapper extends PodioFieldMapper<Period, String> {

	@Override
	protected Period doMap(WrappedField fieldWrapper) {
		Period result = new Period();
		result.setStart(parseDate(fieldWrapper, "[start_utc]"));
		result.setEnd(parseDate(fieldWrapper, "[end_utc]"));
		return result;
	}

	private DateTime parseDate(WrappedField fieldWrapper, String path) {
		String dateString = fieldWrapper.getFieldValue(path, String.class);
		if (dateString != null) {
			return DateTimeUtil.parseDateTime(dateString);
		}
		return null;
	}

	@Override
	public Map<String, String> mapBack(Period value) {
		Map<String, String> result = new HashMap<String, String>();
		result.putAll(mapPeriodPart("start", value.getStart()));
		if (value.getEnd() != null) {
			result.putAll(mapPeriodPart("end", value.getEnd()));
		}
		return result;
	}

	private Map<String, String> mapPeriodPart(String prefix, DateTime value) {
		Map<String, String> result = new HashMap<String, String>();
		result.put(prefix, DateTimeUtil.formatDateTime(value));
		result.put(prefix + "_date", DateTimeUtil.formatDate(value));
		result.put(prefix + "_time", DateTimeUtil.formatTime(value));
		result.put(prefix + "_utc", DateTimeUtil.formatDateTime(value));
		return result;
	}
}
