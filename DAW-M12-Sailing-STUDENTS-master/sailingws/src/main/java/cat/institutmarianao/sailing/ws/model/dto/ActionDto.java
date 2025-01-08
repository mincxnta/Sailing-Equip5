package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.sailing.ws.model.Action;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JSON annotations */
/*
 * Maps JSON data to OpeningDto, AssignmentDto, InterventionDto or CloseDto instance
 * depending on property type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = BookingDto.class, name = Action.BOOKING),
		@JsonSubTypes.Type(value = CancellationDto.class, name = Action.CANCELLATION),
		@JsonSubTypes.Type(value = DoneDto.class, name = Action.DONE),
		@JsonSubTypes.Type(value = ReschedulingDto.class, name = Action.RESCHEDULING) })
/* Validation */
/* Lombok */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	@NotNull
	/* Lombok */
	@JsonIgnore
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	@NotNull
	/* Lombok */
	@NonNull
	protected Action.Type type;

	/* Validation */
	@NotNull
	protected String performer;

	protected Date date = new Date();

	/* Validation */
	@NotNull
	/* JSON */
	protected Long tripId;

	private String comments;
}
