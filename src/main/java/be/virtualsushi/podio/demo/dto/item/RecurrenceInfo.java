package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;

import lombok.Data;

import org.joda.time.DateTime;

import be.virtualsushi.podio.demo.serialize.annotation.DateShort;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@JsonInclude(Include.NON_NULL)
public class RecurrenceInfo implements Serializable {

	private static final long serialVersionUID = 6112847507302850181L;

	private RecurrenceTypes name;

	private RecurrenceConfig config;

	private Integer step;

	@DateShort
	private DateTime until;

}
