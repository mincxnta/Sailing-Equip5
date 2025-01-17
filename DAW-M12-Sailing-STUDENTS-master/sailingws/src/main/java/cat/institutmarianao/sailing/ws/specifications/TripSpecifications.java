package cat.institutmarianao.sailing.ws.specifications;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;

public class TripSpecifications {
	public static Specification<Trip> hasStatus(Status status) {
		return (root, query, criteriaBuilder) -> status == null ? null
				: criteriaBuilder.equal(root.get("status"), status);
	}

	public static Specification<Trip> hasDateBetween(Date startDate, Date endDate) {
		return (root, query, criteriaBuilder) -> {
			if (startDate == null && endDate == null) {
				return null;
			} else if (startDate != null && endDate != null) {
				return criteriaBuilder.between(root.get("date"), startDate, endDate);
			} else if (startDate != null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDate);
			} else {
				return criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate);
			}
		};
	}
}
