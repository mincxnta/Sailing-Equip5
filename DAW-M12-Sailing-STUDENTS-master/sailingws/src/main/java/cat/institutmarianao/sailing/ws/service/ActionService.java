package cat.institutmarianao.sailing.ws.service;

import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cat.institutmarianao.sailing.ws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActionService {

	Page<Action> findByTripId(Long id, Pageable pagination);

	Action save(@NotNull @Valid Action action) throws ParseException;

}
