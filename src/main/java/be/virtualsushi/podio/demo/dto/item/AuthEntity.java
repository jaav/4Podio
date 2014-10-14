package be.virtualsushi.podio.demo.dto.item;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthEntity {

	private Integer id;

	private String name;

}
