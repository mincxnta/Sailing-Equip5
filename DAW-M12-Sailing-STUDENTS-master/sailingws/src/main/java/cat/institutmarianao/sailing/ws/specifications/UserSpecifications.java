package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.User.Role;

public class UserSpecifications {
	public static Specification<User> hasRole(Role role) {
		return (root, query, criteriaBuilder) -> role == null ? null : criteriaBuilder.equal(root.get("role"), role);
	}
}
