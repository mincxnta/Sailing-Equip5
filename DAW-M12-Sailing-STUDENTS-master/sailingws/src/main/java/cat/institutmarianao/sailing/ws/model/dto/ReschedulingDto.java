package cat.institutmarianao.sailing.ws.model.dto;

import java.util.Date;

import cat.institutmarianao.sailing.ws.model.Action.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReschedulingDto extends ActionDto {
	private static final long serialVersionUID = 1L;

	private Date newDate;

	private Date newDeparture;

	public ReschedulingDto() {
		super(Type.RESCHEDULING);
	}
}
