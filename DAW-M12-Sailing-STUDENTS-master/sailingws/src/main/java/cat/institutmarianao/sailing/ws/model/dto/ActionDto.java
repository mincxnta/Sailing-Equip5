package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import cat.institutmarianao.sailing.ws.model.Action;
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
/* Validation */
/* Lombok */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	/* Lombok */
	@NonNull
	protected Action.Type type;

	/* Validation */
	protected String performer;

	protected Date date = new Date();

	/* Validation */
	/* JSON */
	protected Long tripId;

	private String comments;
}
