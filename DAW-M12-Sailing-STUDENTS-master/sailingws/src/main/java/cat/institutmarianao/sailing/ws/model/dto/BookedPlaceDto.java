package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private Long tripTypeId;

	/* Validation */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.DATE_PATTERN)
	private Date date;

	private Date departure;

	private long bookedPlaces;
}
