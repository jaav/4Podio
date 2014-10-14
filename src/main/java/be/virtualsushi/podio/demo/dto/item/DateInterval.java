package be.virtualsushi.podio.demo.dto.item;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateInterval implements Serializable {

	private static final long serialVersionUID = -519238804414250570L;

	private DateTime from;

	private DateTime to;

}
