package cat.institutmarianao.sailing.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import cat.institutmarianao.sailing.model.Action;
import cat.institutmarianao.sailing.model.BookedPlace;
import cat.institutmarianao.sailing.model.Trip;
import cat.institutmarianao.sailing.model.TripType;
import cat.institutmarianao.sailing.services.TripService;
import jakarta.validation.constraints.NotNull;

public class TripServiceImpl implements TripService {
	private static final String USERNAME = "username";
	// Long?
	private static final String TRIP_ID = "tripId";
	private static final String TRIP_TYPE_ID = "trip_type_id";
	private static final String DATE = "date";
	private static final String CATEGORY = "category";

	private static final String TRIPS_SERVICE = "/trips";
	private static final String TRIP_TYPES_SERVICE = "/triptypes";

	private static final String TRIPS_FIND_TRACKING_BY_ID = TRIPS_SERVICE + "/find/tracking/by/id/{" + TRIP_ID + "}";
	private static final String TRIPS_FIND_ALL = TRIPS_SERVICE + "/find/all";
	private static final String TRIPS_FIND_ALL_BY_CLIENT_USERNAME = TRIPS_SERVICE + "/find/all/by/client/username/{"
			+ USERNAME + "}";
	private static final String TRIPS_FIND_BOOKED_PLACES_BY_TRIP_TYPE_ID_DATE = TRIPS_SERVICE + "/bookedPlaces{"
			+ TRIP_TYPE_ID + "}/{" + DATE + "}";
	private static final String TRIPS_SAVE = TRIPS_SERVICE + "/save";
	private static final String TRIPS_SAVE_ACTION = TRIPS_SERVICE + "/save/action";

	private static final String TRIP_TYPES_GET_BY_ID = TRIP_TYPES_SERVICE + "/get/by/id{" + TRIP_TYPE_ID + "}";
	private static final String TRIP_TYPES_FIND_ALL = TRIP_TYPES_SERVICE + "/find/all";
	private static final String TRIP_TYPES_FIND_ALL_BY_CATEGORY = TRIP_TYPES_SERVICE + "/find/all/{" + CATEGORY + "}";

	@Value("${webService.host}")
	private String webServiceHost;

	@Value("${webService.port}")
	private String webServicePort;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Trip> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trip> findAllByClientUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trip save(Trip trip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookedPlace> findBookedPlacesByTripIdAndDate(@NotNull Long id, @NotNull Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripType> getAllTripTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripType> getAllGroupTripTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripType> getAllPrivateTripTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripType getTripTypeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findTrackingById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action track(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

}
