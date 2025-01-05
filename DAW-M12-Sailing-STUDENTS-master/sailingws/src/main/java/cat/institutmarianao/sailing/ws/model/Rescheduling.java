package cat.institutmarianao.sailing.ws.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Validation */
//TODO REVISAR
/* JPA annotations */
@Entity
@DiscriminatorValue("RESCHEDULING")
/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rescheduling extends Action {
	private static final long serialVersionUID = 1L;

	/* Validation */
	@NotNull
	@Future
	/* JPA */
	@Column(name = "new_date")
	@Temporal(TemporalType.DATE)
	private Date newDate;

	/* Validation */
	@NotNull
	/* JPA */
	@Column(name = "new_departure")
	@Temporal(TemporalType.TIME)
	private Date newDeparture;
}
