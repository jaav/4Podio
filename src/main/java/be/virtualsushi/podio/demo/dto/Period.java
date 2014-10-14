package be.virtualsushi.podio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

import be.virtualsushi.podio.demo.serialize.DateTimeUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Period {

	private DateTime start;

	private DateTime end;

	public boolean contains(DateTime date) {
		return date == null || (start == null || date.isAfter(start)) && (end == null || date.isBefore(end));
	}

	public String toUrlParam() {
		return (start != null ? DateTimeUtil.formatDate(start) : DateTimeUtil.formatDate(new DateTime().year().addToCopy(-10))) + "-"
				+ (end != null ? DateTimeUtil.formatDate(end) : DateTimeUtil.formatDate(new DateTime().year().addToCopy(10)));
	}

	public boolean isNotNull() {
		return start != null && end != null;
	}
}
