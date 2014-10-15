package be.virtualsushi.podio.demo.serialize;

import java.io.IOException;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TimeZoneSerializer extends StdSerializer<TimeZone> {

	public TimeZoneSerializer() {
		super(TimeZone.class);
	}

	@Override
	public void serialize(TimeZone value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(value.getID());
	}
}
