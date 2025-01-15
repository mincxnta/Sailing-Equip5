package cat.institutmarianao.sailing.ws.error.utils;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.ConstraintViolationException;

public class ErrorUtils {

	private ErrorUtils() {
	}

	public static void checkDepartures(Date departure, Trip trip) {

		if (trip.getType().getCategory().equals(Category.GROUP)) {
			List<Date> departures = trip.getType().getDepartures();
			String str = departures.toString();
			if (departure != null) {
				checkDeparture(departure, departures);
			}
			checkDeparture(trip.getDeparture(), departures);
		}
	}

	private static void checkDeparture(Date departure, List<Date> departures) {
		if (!departures.contains(departure)) {
			throw new ConstraintViolationException("Invalid departure", null);
		}
	}
}
