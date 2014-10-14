package be.virtualsushi.podio.demo.dto.file;

import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
public class FileSpaceRef {

	@JsonProperty("ref_type")
	private String type = "space";

	@JsonProperty("ref_id")
	private Integer id;

	public FileSpaceRef(Integer id) {
		this.id = id;
	}

}
