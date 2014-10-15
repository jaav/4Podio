package be.virtualsushi.podio.demo.web.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class PlainBytesMessageConverter extends AbstractHttpMessageConverter<byte[]> {

	public PlainBytesMessageConverter() {
		super(MediaType.ALL, MediaType.ALL);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return byte[].class.equals(clazz);
	}

	@Override
	public byte[] readInternal(Class<? extends byte[]> clazz, HttpInputMessage inputMessage) throws IOException {
		long contentLength = inputMessage.getHeaders().getContentLength();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(contentLength >= 0 ? (int) contentLength : StreamUtils.BUFFER_SIZE);
		StreamUtils.copy(inputMessage.getBody(), bos);
		return bos.toByteArray();
	}

	@Override
	protected Long getContentLength(byte[] bytes, MediaType contentType) {
		return (long) bytes.length;
	}

	@Override
	protected void writeInternal(byte[] bytes, HttpOutputMessage outputMessage) throws IOException {
		StreamUtils.copy(bytes, outputMessage.getBody());
	}
}
