package be.virtualsushi.podio.demo.dto.file;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileProxy implements Serializable {

	private static final long serialVersionUID = 6034734304387708922L;

	private FileInfo fileInfo;

	private byte[] content;

}
