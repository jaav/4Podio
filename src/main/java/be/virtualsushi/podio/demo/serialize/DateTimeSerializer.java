package be.virtualsushi.podio.demo.serialize;

import java.io.IOException;

import org.joda.time.DateTime;

import be.virtualsushi.podio.demo.serialize.annotation.DateShort;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateTimeSerializer extends StdSerializer<DateTime> implements ContextualSerializer {

	private boolean shortFormat;

	public DateTimeSerializer() {
		super(DateTime.class);
	}

	@Override
	public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (shortFormat) {
			jgen.writeString(DateTimeUtil.formatDate(value));
		} else {
			jgen.writeString(DateTimeUtil.formatDateTime(value));
		}
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
		if (property.getAnnotation(DateShort.class) != null) {
			shortFormat = true;
		} else {
			shortFormat = false;
		}
		return this;
	}

}
