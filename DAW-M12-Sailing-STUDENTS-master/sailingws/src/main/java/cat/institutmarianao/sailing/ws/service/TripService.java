package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import cat.institutmarianao.sailing.ws.model.Client;
import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.validation.constraints.NotBlank;

public interface TripService {
	List<Trip> findAll();
	
	List<Trip> findAllByClientUsername(String username);

}
