package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.model.dto.TripTypeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface TripTypeService {
	List<TripType> findAll();
	
	TripType findById(@NotNull Long id);
	
	List<TripType> findAllTripTypesByCategory(Category category);
	
	//List<TripType> findAllGroupTripTypes();
}
