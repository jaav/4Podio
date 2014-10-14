package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public abstract class AppItem implements Serializable {

	private static final long serialVersionUID = -4564606611956921424L;

	private Integer id;

	private String title;

	private String description;

	private List<String> tags;

	private List<Rights> rights;

}
