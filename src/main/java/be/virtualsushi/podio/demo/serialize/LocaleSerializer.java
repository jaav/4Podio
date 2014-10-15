package be.virtualsushi.podio.demo.serialize;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocaleSerializer extends StdSerializer<Locale> {

	public LocaleSerializer() {
		super(Locale.class);
	}

	@Override
	public void serialize(Locale value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(value.getLanguage() + "_" + value.getCountry());
	}

}
