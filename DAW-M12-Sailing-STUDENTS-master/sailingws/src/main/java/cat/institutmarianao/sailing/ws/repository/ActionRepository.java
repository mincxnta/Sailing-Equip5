package cat.institutmarianao.sailing.ws.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.sailing.ws.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {
	Page<Action> findByTripId(Long id, Pageable pagination);
}
