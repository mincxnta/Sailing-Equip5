package cat.institutmarianao.sailing.ws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface TripService {

	Page<Trip> findAllByClientUsername(String username, Pageable pagination);

	Trip findById(Long id);

	boolean existsById(@NotNull Long id);

	Trip save(@NotNull @Valid Trip trip);

	Page<Trip> getReservedTrips(Pageable pagination);
}