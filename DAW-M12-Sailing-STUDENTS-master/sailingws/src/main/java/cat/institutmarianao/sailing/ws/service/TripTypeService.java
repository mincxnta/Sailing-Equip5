package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.constraints.NotNull;

public interface TripTypeService {
	List<TripType> findAll();

	TripType findById(@NotNull Long id);

	List<TripType> findAllTripTypesByCategory(Category category);

}
