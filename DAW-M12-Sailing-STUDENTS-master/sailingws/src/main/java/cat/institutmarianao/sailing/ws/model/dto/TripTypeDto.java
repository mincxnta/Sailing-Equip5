package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.TripType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	private String title;

	/* Validation */
	private TripType.Category category;

	/* Validation */
	private String description;

	/* Validation */
	private double price;

	/* JSON */
	private List<Date> departures;

	/* Validation */
	private int duration;

	/* Validation */
	private int maxPlaces;
}
