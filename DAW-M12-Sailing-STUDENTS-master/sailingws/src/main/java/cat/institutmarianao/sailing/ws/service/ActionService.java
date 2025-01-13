package cat.institutmarianao.sailing.ws.service;

import java.text.ParseException;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActionService {

	List<Action> findByTripId(Long id);

	Action save(@NotNull @Valid Action action) throws ParseException;

}
