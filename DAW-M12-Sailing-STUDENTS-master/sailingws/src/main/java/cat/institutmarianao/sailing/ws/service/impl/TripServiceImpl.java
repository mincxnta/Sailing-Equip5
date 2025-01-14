package cat.institutmarianao.sailing.ws.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.error.utils.ErrorUtils;
import cat.institutmarianao.sailing.ws.exception.ForbiddenException;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.service.BookedPlaceService;
import cat.institutmarianao.sailing.ws.service.TripService;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripServiceImpl implements TripService {
	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private BookedPlaceService bookedPlaceService;

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
		return tripRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trip with ID " + id + " does not exist."));
	}

	@Override
	public boolean existsById(@NotNull Long id) {
		return tripRepository.existsById(id);
	}

	@Override
	@Validated(OnTripCreate.class)
	public Trip save(@NotNull @Valid Trip trip) {

		BookedPlace bookedPlace = bookedPlaceService.findByIdTripTypeIdAndIdDateAndIdDeparture(trip.getType().getId(),
				trip.getDate(), trip.getDeparture());

		long maxPlaces = trip.getType().getMaxPlaces();
		long bookedPlaces = bookedPlace.getBookedPlaces();
		long availablePlaces = maxPlaces - bookedPlaces;
		if (availablePlaces < trip.getPlaces()) {
			throw new ConstraintViolationException(
					"There aren't enough places for this trip, only " + availablePlaces + " places left.", null);
		}

		if (trip.getDate().before(new Date())) {
			throw new ForbiddenException("You can't book a past trip");
		}

		ErrorUtils.checkDepartures(null, trip);
		Trip ret = tripRepository.saveAndFlush(trip);
		return ret;
	}
}