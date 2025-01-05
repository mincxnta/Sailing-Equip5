package cat.institutmarianao.sailing.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.model.BookedPlaceCompositeId;
import cat.institutmarianao.sailing.ws.model.Trip;

public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action>{
	List<Action> findByTripId(Long id);
}
