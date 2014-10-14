package be.virtualsushi.podio.demo.dto.file;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileLink implements Serializable {

	private static final long serialVersionUID = -2530609167009435614L;

	@JsonProperty("file_id")
	private Integer id;

	private String name;

	private String link;

	@JsonProperty("mimetype")
	private String mimeType;

}
