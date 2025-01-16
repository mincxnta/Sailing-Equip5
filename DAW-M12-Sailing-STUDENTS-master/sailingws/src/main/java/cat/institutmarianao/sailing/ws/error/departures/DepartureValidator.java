package cat.institutmarianao.sailing.ws.error.departures;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.ConstraintViolationException;

public class DepartureValidator {

	private DepartureValidator() {
	}

	public static void validateDepartures(Date departure, Trip trip) {

		if (trip.getType().getCategory().equals(Category.GROUP)) {
			List<Date> departures = trip.getType().getDepartures();
			String str = departures.toString();
			if (departure != null) {
				checkIfDepartureExists(departure, departures);
			}
			checkIfDepartureExists(trip.getDeparture(), departures);
		}
	}

	private static void checkIfDepartureExists(Date departure, List<Date> departures) {
		if (!departures.contains(departure)) {
			throw new ConstraintViolationException("Invalid departure", null);
		}
	}
}
