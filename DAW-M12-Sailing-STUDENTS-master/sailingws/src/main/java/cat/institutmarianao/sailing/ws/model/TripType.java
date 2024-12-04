package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* JPA annotations */
@Entity
@Table(name = "trip_types")
/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripType implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GROUP = "GROUP";
	public static final String PRIVATE = "PRIVATE";

	public enum Category {
		GROUP, PRIVATE
	}

	/* Validation */
	/* JPA */
	/* Lombok */
	@EqualsAndHashCode.Include
	@Column
	private Long id;

	/* Validation */
	@Column
	private String title;

	/* Validation */
	/* JPA */
	@Column
	private Category category;

	/* Validation */
	@Column
	private String description;

	/* Validation */
	@Column
	private double price;

	/* JPA */
	private List<Date> departures;

	/* Validation */
	@Column
	private int duration;

	/* Validation */
	/* JPA */
	@Column(name = "max_places")
	private int maxPlaces;
}
