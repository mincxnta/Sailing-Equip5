package cat.institutmarianao.sailing.ws.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
/* JPA annotations */
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Done extends Action {
	private static final long serialVersionUID = 1L;

	public Done() {
		super(Type.DONE);
	}
}
