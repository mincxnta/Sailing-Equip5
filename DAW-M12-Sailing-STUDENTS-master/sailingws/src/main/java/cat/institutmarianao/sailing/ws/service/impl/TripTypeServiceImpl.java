package cat.institutmarianao.sailing.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripTypeRepository;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import cat.institutmarianao.sailing.ws.specifications.TripTypeSpecifications;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripTypeServiceImpl implements TripTypeService {
	@Autowired
	private TripTypeRepository tripTypeRepository;

	@Override
	public Page<TripType> findAll(Double minPrice, Double maxPrice, Pageable pagination) {
		Specification<TripType> spec = Specification.where(TripTypeSpecifications.hasPriceBetween(minPrice, maxPrice));

		return tripTypeRepository.findAll(spec, pagination);
	}

	@Override
	public TripType findById(@NotNull Long id) {
		return tripTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Trip type with ID " + id + " does not exist."));
	}

	@Override
	public Page<TripType> findAllTripTypesByCategory(Category category, Pageable pagination) {
		return tripTypeRepository.findAllTripTypesByCategory(category, pagination);
	}
}
