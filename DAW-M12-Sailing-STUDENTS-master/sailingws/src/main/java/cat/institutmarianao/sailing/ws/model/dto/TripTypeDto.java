package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.TripType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@NotNull
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	@NotBlank
	private String title;

	/* Validation */
	@NotNull
	private TripType.Category category;

	/* Validation */
	@Max(value = 255)
	private String description;

	/* Validation */
	@Positive
	private double price;

	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.TIME_PATTERN)
	private List<Date> departures;

	/* Validation */
	@Min(value = 1)
	private int duration;

	/* Validation */
	@Positive
	private int maxPlaces;
}
