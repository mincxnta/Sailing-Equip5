package cat.institutmarianao.sailing.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cat.institutmarianao.sailing.model.Action;
import cat.institutmarianao.sailing.model.BookedPlace;
import cat.institutmarianao.sailing.model.Trip;
import cat.institutmarianao.sailing.model.TripType;
import cat.institutmarianao.sailing.model.TripType.Category;
import cat.institutmarianao.sailing.services.TripService;
import jakarta.validation.constraints.NotNull;

@Service
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
		final String uri = webServiceHost + ":" + webServicePort + TRIPS_FIND_ALL;

		ResponseEntity<Trip[]> response = restTemplate.getForEntity(uri, Trip[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Trip> findAllByClientUsername(String username) {
		final String baseUri = webServiceHost + ":" + webServicePort + TRIPS_FIND_ALL_BY_CLIENT_USERNAME;
		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put(USERNAME, username);

		ResponseEntity<Trip[]> response = restTemplate
				.getForEntity(uriTemplate.buildAndExpand(uriVariables).toUriString(), Trip[].class);
		return Arrays.asList(response.getBody());
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
		final String uri = webServiceHost + ":" + webServicePort + TRIP_TYPES_FIND_ALL;
		ResponseEntity<TripType[]> response = restTemplate.getForEntity(uri, TripType[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<TripType> getAllGroupTripTypes() {
		final String baseUri = webServiceHost + ":" + webServicePort + TRIP_TYPES_FIND_ALL_BY_CATEGORY;

		Map<String, TripType.Category> uriVariables = new HashMap<>();
		uriVariables.put(CATEGORY, Category.GROUP);

		ResponseEntity<List<TripType>> responseEntity = restTemplate.exchange(baseUri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TripType>>() {
				}, uriVariables);

		return responseEntity.getBody();
	}

	@Override
	public List<TripType> getAllPrivateTripTypes() {
		final String baseUri = webServiceHost + ":" + webServicePort + TRIP_TYPES_FIND_ALL_BY_CATEGORY;

		Map<String, TripType.Category> uriVariables = new HashMap<>();
		uriVariables.put(CATEGORY, Category.PRIVATE);

		ResponseEntity<List<TripType>> responseEntity = restTemplate.exchange(baseUri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TripType>>() {
				}, uriVariables);

		return responseEntity.getBody();
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
