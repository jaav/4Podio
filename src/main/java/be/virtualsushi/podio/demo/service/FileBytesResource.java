package be.virtualsushi.podio.demo.service;

import org.springframework.core.io.ByteArrayResource;

public class FileBytesResource extends ByteArrayResource {

	private String filename;

	public FileBytesResource(String filename, byte[] byteArray) {
		super(byteArray);
		this.filename = filename;
	}

	@Override
	public String getFilename() {
		return filename;
	}

}
