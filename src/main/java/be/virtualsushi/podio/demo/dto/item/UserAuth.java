package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuth implements Serializable {

	private static final long serialVersionUID = -7485257936825778680L;

	private final String type = "user";

	private Integer id;

}
