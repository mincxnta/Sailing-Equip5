package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import cat.institutmarianao.sailing.ws.model.Trip;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	/* JSON */
	private Long id;

	/* JPA */
	private Long typeId;

	/* Validation */
	private String clientUsername;

	private int places;

	/* Lombok */
	private Trip.Status status;

	/* Validation */
	/* JSON */
	private Date date;

	/* JSON */
	private Date departure;
}
