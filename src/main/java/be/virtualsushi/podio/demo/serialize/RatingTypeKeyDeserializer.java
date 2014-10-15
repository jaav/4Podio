package be.virtualsushi.podio.demo.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.podio.rating.RatingType;

public class RatingTypeKeyDeserializer extends KeyDeserializer {

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return RatingType.getByName(key);
	}
}
