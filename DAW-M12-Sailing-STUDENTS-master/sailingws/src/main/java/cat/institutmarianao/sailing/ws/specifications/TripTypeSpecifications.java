package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.TripType;

public class TripTypeSpecifications {
	public static Specification<TripType> hasPriceBetween(Double minPrice, Double maxPrice) {
		return (root, query, criteriaBuilder) -> {
			if (minPrice == null && maxPrice == null) {
				return null;
			} else if (minPrice != null && maxPrice != null) {
				return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
			} else if (minPrice != null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
			} else {
				return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
			}
		};
	}
}
