package cat.institutmarianao.sailing.ws.model;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Validation */
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
	/* JPA */
	private Date newDate;

	/* Validation */
	/* JPA */
	private Date newDeparture;
}
