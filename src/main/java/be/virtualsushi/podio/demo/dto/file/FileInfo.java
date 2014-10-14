package be.virtualsushi.podio.demo.dto.file;

import java.io.Serializable;

import lombok.Data;

@Data
public class FileInfo implements Serializable {

	private static final long serialVersionUID = 3297608559926097198L;

	private String name;

	private String cacheKey;

	private String contentType;

}
