package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface TripService {

	List<Trip> findAllByClientUsername(String username);

	Trip findById(Long id);

	boolean existsById(@NotNull Long id);

	Trip save(@NotNull @Valid Trip trip);

	List<Trip> getReservedTrips();
}