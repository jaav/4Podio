package be.virtualsushi.podio.demo.dto.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;

import be.virtualsushi.podio.demo.dto.Profile;
import be.virtualsushi.podio.demo.dto.item.AppItem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true, of = {})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class File extends AppItem implements Serializable {

	public static final String PUBLIC_TAG = "public";
	public static final String PERSONAL_TAG = "personal";

	private static final long serialVersionUID = 322243952935606962L;

	@JsonProperty("created_by")
	private Profile createdBy;

	@JsonProperty("created_on")
	private DateTime createdOn;

	private FileInfo proxy;

	private List<FileLink> files;

	private boolean personal;

	public void addFile(FileLink file) {
		if (files == null) {
			files = new ArrayList<>();
		}
		files.add(file);
	}

	public void addTag(String tag) {
		if (getTags() == null) {
			setTags(new ArrayList<String>());
		}
		getTags().add(tag);
	}

}
