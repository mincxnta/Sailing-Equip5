package cat.institutmarianao.sailing.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.PathVariable;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.model.dto.TripTypeDto;

public interface TripTypeRepository extends JpaRepository<TripType, Long>, JpaSpecificationExecutor<TripType> {
	List<TripType> findAllTripTypesByCategory(Category category);
	
	//List<TripType> findAllGroupTripTypes();

}
