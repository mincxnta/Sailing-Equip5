package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.dto.ActionDto;
import cat.institutmarianao.sailing.ws.model.dto.TripDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface TripService {
	List<Trip> findAll();
	
	List<Trip> findAllByClientUsername(String username);

	Trip findById(Long id);

	boolean existsById(@NotNull Long id);

	Trip save(@NotNull @Valid Trip trip);
}