package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.sailing.ws.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JSON annotations */
/*
 * Maps JSON data to Employee, Technician or Supervisor instance depending on
 * property role
 */

/* Lombok */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@NonNull
	@EqualsAndHashCode.Include
	protected String username;

	/* Validation */
	/* Lombok */
	@NonNull
	protected String password;

	/* Validation */
	protected User.Role role;
}
