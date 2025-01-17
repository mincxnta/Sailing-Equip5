package cat.institutmarianao.sailing.ws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.constraints.NotNull;

public interface TripTypeService {
	Page<TripType> findAll(Pageable pagination);

	TripType findById(@NotNull Long id);

	Page<TripType> findAllTripTypesByCategory(Category category, Pageable pagination);

}
