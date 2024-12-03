package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JPA annotations */
/* Mapping JPA Indexes */
/* JPA Inheritance strategy is single table */
/*
 * Maps different JPA objects depDONEing on his type attribute (Opening,
 * Assignment, Intervention or Close)
 */
/* Lombok */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String BOOKING = "BOOKING";
	public static final String RESCHEDULING = "RESCHEDULING";
	public static final String CANCELLATION = "CANCELLATION";
	public static final String DONE = "DONE";

	public enum Type {
		BOOKING, RESCHEDULING, CANCELLATION, DONE
	}

	/* Validation */
	/* JPA */
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	/* Lombok */
	@NonNull
	/* JPA */
	protected Type type;

	/* Validation */
	/* JPA */
	protected User performer;

	/* JPA */
	protected Date date = new Date();

	/* Validation */
	/* JPA */
	/* JSON */
	protected Trip trip;

	private String comments;
}
