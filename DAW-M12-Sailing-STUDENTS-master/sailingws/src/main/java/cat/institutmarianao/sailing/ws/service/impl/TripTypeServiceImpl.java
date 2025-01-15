package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripTypeRepository;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripTypeServiceImpl implements TripTypeService {
	@Autowired
	private TripTypeRepository tripTypeRepository;

	@Override
	public List<TripType> findAll() {
		return tripTypeRepository.findAll();
	}

	@Override
	public TripType findById(@NotNull Long id) {
		return tripTypeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trip type with ID " + id + " does not exist."));
	}

	@Override
	public List<TripType> findAllTripTypesByCategory(Category category) {
		return tripTypeRepository.findAllTripTypesByCategory(category);
	}
}
