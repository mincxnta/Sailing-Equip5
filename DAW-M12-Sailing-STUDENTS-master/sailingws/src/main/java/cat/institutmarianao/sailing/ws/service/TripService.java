package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.dto.TripDto;

public interface TripService {
	List<Trip> findAll();
	
	List<Trip> findAllByClientUsername(String username);

	TripDto save(TripDto tripDto);
}
