package cat.institutmarianao.sailing.ws.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.error.departures.DepartureValidator;
import cat.institutmarianao.sailing.ws.exception.ForbiddenException;
import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Booking;
import cat.institutmarianao.sailing.ws.model.Cancellation;
import cat.institutmarianao.sailing.ws.model.Rescheduling;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.User.Role;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.service.ActionService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class ActionServiceImpl implements ActionService {
	@Autowired
	private ActionRepository actionRepository;

	@Override
	public Page<Action> findByTripId(Long id, Pageable pagination) {
		return actionRepository.findByTripId(id, pagination);
	}

	@Override
	public Action save(@NotNull @Valid Action action) throws ParseException {
		Status status = action.getTrip().getStatus();

		validatePerformer(action);
		validateFinishedTrips(status);

		if (action instanceof Booking) {
			throw new ConstraintViolationException("Trip is already booked", null);
		} else if (action instanceof Cancellation) {
			validateCancellation(action);
		} else if (action instanceof Rescheduling rescheduling) {
			DepartureValidator.validateDepartures(rescheduling.getNewDeparture(), rescheduling.getTrip());
		} else {
			validateDone(action);
		}

		return actionRepository.saveAndFlush(action);
	}

	private void validateCancellation(Action action) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(SailingWsApplication.DATE_PATTERN);
		Date tripDate = action.getTrip().getDate();
		Date today = sdf.parse(sdf.format(new Date()));

		if (Duration.ofMillis(tripDate.getTime() - today.getTime()).toHours() < 48) {
			throw new ConstraintViolationException("Trips can only be cancelled up to 48h in advance", null);
		}
	}

	private void validateDone(Action action) {
		if (action.getDate().before(action.getTrip().getDate())) {
			throw new ForbiddenException("You can't finish a future trip");
		}
	}

	private void validateFinishedTrips(Status status) {
		if (status.equals(Status.CANCELLED) || status.equals(Status.DONE)) {
			throw new ConstraintViolationException("Trip is finished", null);
		}
	}

	private void validatePerformer(Action action) {
		if (!action.getPerformer().equals(action.getTrip().getClient())
				&& action.getPerformer().getRole().equals(Role.CLIENT)) {
			throw new ForbiddenException("You can't change another client's trip status");
		}
	}
}
