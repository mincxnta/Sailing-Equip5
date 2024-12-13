package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.model.Client;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.model.dto.BookedPlaceDto;
import cat.institutmarianao.sailing.ws.model.dto.TripDto;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.service.TripService;
import jakarta.validation.constraints.NotBlank;

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

//	@Override
//	public TripDto save(TripDto tripDto) {
//		return tripRepository.save(tripDto);
//	}
}