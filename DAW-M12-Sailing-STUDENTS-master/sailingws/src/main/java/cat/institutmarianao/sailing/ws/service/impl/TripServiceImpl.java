package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.service.TripService;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripServiceImpl implements TripService {
	@Autowired
	private TripRepository tripRepository;

	@Override
	public List<Trip> findAll() {
		return tripRepository.findAll();
	}

	@Override
	public List<Trip> findAllByClientUsername(String username) {
		return tripRepository.findAllByClientUsername(username);
	}

	@Override
	public Trip findById(Long id) {
		return tripRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean existsById(@NotNull Long id) {
		return tripRepository.existsById(id);
	}

	@Override
	@Validated(OnTripCreate.class)
	public Trip save(@NotNull @Valid Trip trip) {
		Trip ret = tripRepository.saveAndFlush(trip);
		return ret;
	}
}