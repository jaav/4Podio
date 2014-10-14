package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CategoryOption implements Serializable {

	private static final long serialVersionUID = 1472233990033158688L;

	private int id;

	private CategoryOptionStatus status;

	private String text;

	private String color;

}
