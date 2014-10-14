package be.virtualsushi.podio.demo.serialize;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateTimeUtil {

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.UTC);

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd").withZone(DateTimeZone.UTC);

	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm:ss").withZone(DateTimeZone.UTC);

	private DateTimeUtil() {
	}

	public static DateTime parseDateTime(String text) {
		try {
			return DATE_TIME_FORMAT.parseDateTime(text);
		} catch (IllegalArgumentException e) {
			return DATE_FORMAT.parseDateTime(text);
		}
	}

	public static String formatDateTime(DateTime dateTime) {
		return DATE_TIME_FORMAT.print(dateTime);
	}

	public static LocalDate parseDate(String text) {
		return DATE_FORMAT.parseDateTime(text).toLocalDate();
	}

	public static String formatDate(LocalDate date) {
		return DATE_FORMAT.print(date);
	}

	public static String formatDate(DateTime date) {
		return DATE_FORMAT.print(date);
	}

	public static String formatTime(DateTime date) {
		return TIME_FORMAT.print(date);
	}
}
