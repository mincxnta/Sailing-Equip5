package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.sailing.ws.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ClientDto.class, name = User.CLIENT),
		@JsonSubTypes.Type(value = AdminDto.class, name = User.ADMIN) })
public abstract class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Validation */
	@NotBlank
	/* Lombok */
	@NonNull
	@EqualsAndHashCode.Include
	protected String username;

	/* Validation */
	@NotBlank
	/* Lombok */
	@JsonProperty(access = Access.WRITE_ONLY)
	@NonNull
	protected String password;

	/* Validation */
	@NotNull
	protected User.Role role;
}
