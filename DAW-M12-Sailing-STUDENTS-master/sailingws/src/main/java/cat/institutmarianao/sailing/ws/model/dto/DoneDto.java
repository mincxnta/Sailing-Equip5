package cat.institutmarianao.sailing.ws.model.dto;

import cat.institutmarianao.sailing.ws.model.Action.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoneDto extends ActionDto {
	private static final long serialVersionUID = 1L;

	public DoneDto() {
		super(Type.DONE);
	}
}
