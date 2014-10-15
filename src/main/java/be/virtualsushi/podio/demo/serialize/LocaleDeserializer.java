package be.virtualsushi.podio.demo.serialize;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocaleDeserializer extends JsonDeserializer<Locale> {

	@Override
	public Locale deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new Locale(jp.getText());
	}
}
