package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.dto.ActionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ActionService {

	List<Action> findByTripId(Long id);
	
	Action save(@NotNull @Valid Action action);
	
}
