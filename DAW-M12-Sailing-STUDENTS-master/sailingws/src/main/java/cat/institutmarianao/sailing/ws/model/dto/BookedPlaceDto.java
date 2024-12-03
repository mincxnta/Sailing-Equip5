package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookedPlaceDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	private Long tripTypeId;

	/* Validation */
	private Date date;

	private Date departure;

	private long bookedPlaces;
}
