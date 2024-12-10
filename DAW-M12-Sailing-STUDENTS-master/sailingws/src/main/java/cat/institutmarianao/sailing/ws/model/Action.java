package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JPA annotations */
@Entity
@Table(name = "actions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, columnDefinition = "varchar(31)")
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
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	/* Lombok */
	@NonNull
	/* JPA */
	@Column(insertable = false, updatable = false)
	protected Type type;

	/* Validation */
	/* JPA */
	@ManyToOne
	@JoinColumn(name = "performer_username")
	protected User performer;

	/* JPA */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date = new Date();

	/* Validation */
	/* JPA */
	@ManyToOne
	@JoinColumn(name = "trip_id")
	/* JSON */
	protected Trip trip;

	@Column
	private String comments;
}
