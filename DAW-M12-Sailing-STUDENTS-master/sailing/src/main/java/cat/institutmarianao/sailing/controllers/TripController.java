package cat.institutmarianao.sailing.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cat.institutmarianao.sailing.model.Action;
import cat.institutmarianao.sailing.model.BookedPlace;
import cat.institutmarianao.sailing.model.Cancellation;
import cat.institutmarianao.sailing.model.Done;
import cat.institutmarianao.sailing.model.Rescheduling;
import cat.institutmarianao.sailing.model.Trip;
import cat.institutmarianao.sailing.model.TripType;
import cat.institutmarianao.sailing.services.TripService;
import cat.institutmarianao.sailing.validation.groups.OnActionCreate;
import cat.institutmarianao.sailing.validation.groups.OnTripCreate;
import cat.institutmarianao.sailing.validation.groups.OnTripCreateDate;
import cat.institutmarianao.sailing.validation.groups.OnTripCreateDeparture;
import jakarta.validation.constraints.Positive;

@Controller
@SessionAttributes({ "trip", "tripType", "freePlaces", "tripFreePlaces" })
@RequestMapping(value = "/trips")
public class TripController {

	@Autowired
	private TripService tripService;

	@ModelAttribute("trip")
	private Trip setupTrip() {
		return new Trip();
	}

	@ModelAttribute("tripType")
	private TripType setupTripType() {
		return new TripType();
	}

	@ModelAttribute("freePlaces")
	private Map<Date, Long> setupFreePlaces() {
		return new HashMap<>();
	}

	@ModelAttribute("tripFreePlaces")
	private Long setupTripFreePlaces() {
		return 0l;
	}

	@GetMapping("/book/{trip_type_id}")
	public ModelAndView bookSelectDate(@PathVariable(name = "trip_type_id", required = true) Long tripTypeId) {
		// Obtenim l'usuari autenticat
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		TripType triptype = tripService.getTripTypeById(tripTypeId);
		Trip trip = new Trip();
		trip.setTypeId(tripTypeId);
		trip.setClientUsername(authentication.getName());
		trip.setPlaces(0);

		ModelAndView modelview = new ModelAndView("book_date");
		modelview.getModelMap().addAttribute("tripType", triptype);
		modelview.getModelMap().addAttribute("trip", trip);
		return modelview;
		// TODO Error trip type not found
	}

	// TODO Preguntar al Toni cómo funcionan los private trips
	@PostMapping("/book/book_departure")
	public String bookSelectDeparture(@Validated(OnTripCreateDate.class) @ModelAttribute("trip") Trip trip,
			BindingResult result, @SessionAttribute("tripType") TripType tripType,
			@SessionAttribute("freePlaces") Map<Date, Long> freePlaces, ModelMap modelMap) {

		List<String> errors = new ArrayList<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			modelMap.addAttribute("errors", errors);
			return "book_date";
		}

		Date today = new Date();

		if (trip.getDate().before(today)) {
			// TODO Cómo mostrar
			errors.add("book.error.futureDate");
			modelMap.addAttribute("errors", errors);
			return "book_date";
		}

		List<BookedPlace> bookedPlaces = tripService.findBookedPlacesByTripIdAndDate(tripType.getId(), trip.getDate());
		long maxPlaces = tripType.getMaxPlaces();
		freePlaces.clear();
		Set<Date> departures = new HashSet<>(tripType.getDepartures());

		for (Date departure : departures) {
			long availablePlaces = maxPlaces;
			for (BookedPlace bookedPlace : bookedPlaces) {
				if (bookedPlace.getDeparture().equals(departure))
					availablePlaces = maxPlaces - bookedPlace.getBookedPlaces();
			}
			freePlaces.put(departure, availablePlaces);
		}
		return "book_departure";
	}

	@PostMapping("/book/book_places")
	public String bookSelectPlaces(@Validated(OnTripCreateDeparture.class) @ModelAttribute("trip") Trip trip,
			BindingResult result, @SessionAttribute("tripType") TripType tripType,
			@SessionAttribute("freePlaces") Map<Date, Long> freePlaces,
			@SessionAttribute("tripFreePlaces") Long tripFreePlaces, ModelMap modelMap) {

		List<String> errors = new ArrayList<>();

		List<BookedPlace> bookedPlaces = tripService.findBookedPlacesByTripIdAndDate(tripType.getId(), trip.getDate());
		long reservedPlaces = 0;
		for (BookedPlace bookedPlace : bookedPlaces) {
			if (bookedPlace.getDeparture().equals(trip.getDeparture())) {
				reservedPlaces = bookedPlace.getBookedPlaces();
				break;
			}
		}

		tripFreePlaces = tripType.getMaxPlaces() - reservedPlaces;
		modelMap.addAttribute("tripFreePlaces", tripFreePlaces);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			modelMap.addAttribute("errors", errors);
			return "book_departure";
		}

		return "book_places";
	}

	@PostMapping("/book/book_save")
	public String bookSave(@Validated(OnTripCreate.class) @ModelAttribute("trip") Trip trip, BindingResult result,
			@SessionAttribute("tripType") TripType tripType, @SessionAttribute("freePlaces") Map<Date, Long> freePlaces,
			@SessionAttribute("tripFreePlaces") Long tripFreePlaces, ModelMap modelMap, SessionStatus sessionStatus) {
		List<String> errors = new ArrayList<>();
		try {

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
				modelMap.addAttribute("errors", errors);
				return "book_places";
			}
			tripService.save(trip);
			sessionStatus.setComplete();
			return "redirect:/trips/booked";
		} catch (HttpClientErrorException.UnprocessableEntity e) {
			errors.add("book.error.places");
			modelMap.addAttribute("errors", errors);
			return "book_places";
		}
	}

	@GetMapping("/booked")
	public ModelAndView booked() {
		// Obtenim l'usuari autenticat
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		// Creem mapa amb el tripTypeId i el seu TripType corresponent
		List<TripType> allTripTypes = tripService.getAllTripTypes();
		Map<Long, TripType> tripTypes = new HashMap<>();
		for (TripType tripType : allTripTypes) {
			tripTypes.put(tripType.getId(), tripType);
		}

		ModelAndView modelview = new ModelAndView("trips");

		// Inicialitzar accions
		Cancellation cancellation = new Cancellation();
		Done done = new Done();
		Rescheduling rescheduling = new Rescheduling();
		String performer = authentication.getName();

		if (authorities.stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))) {
			modelview.getModelMap().addAttribute("booked_trips", tripService.findAll());

			done.setPerformer(performer);
			rescheduling.setPerformer(performer);
			modelview.getModelMap().addAttribute("rescheduling", rescheduling);
			modelview.getModelMap().addAttribute("done", done);
		} else {
			modelview.getModelMap().addAttribute("booked_trips", tripService.findAllByClientUsername(performer));

			cancellation.setPerformer(authentication.getName());
			modelview.getModelMap().addAttribute("cancellation", cancellation);
		}
		modelview.getModelMap().addAttribute("tripTypes", tripTypes);

		return modelview;
	}

	@PostMapping("/cancel")
	public String cancelTrip(@Validated Cancellation cancellation, RedirectAttributes redirectAttributes) {
		try {
			cancellation.setDate(new Date());
			tripService.track(cancellation);

			return "redirect:/trips/booked";
		} catch (HttpClientErrorException.UnprocessableEntity e) {
			redirectAttributes.addFlashAttribute("error", "trips.cancellation.error");
			return "redirect:/trips/booked";
		}
	}

	@PostMapping("/done")
	public String doneTrip(@Validated(OnActionCreate.class) Done done, RedirectAttributes redirectAttributes) {
		try {
			done.setDate(new Date());
			tripService.track(done);
			return "redirect:/trips/booked";
		} catch (HttpClientErrorException.Forbidden e) {
			redirectAttributes.addFlashAttribute("error", "trips.done.error");

			return "redirect:/trips/booked";
		}
	}

	@PostMapping("/reschedule")
	public String saveAction(@Validated(OnActionCreate.class) Rescheduling rescheduling) {
		rescheduling.setDate(new Date());
		tripService.track(rescheduling);
		return "redirect:/trips/booked";
	}

	@GetMapping("/tracking/{id}")
	public String showContentPart(@PathVariable(name = "id", required = true) @Positive Long id, ModelMap modelMap) {
		List<Action> tracking = tripService.findTrackingById(id);
		modelMap.addAttribute("tripId", id);
		modelMap.addAttribute("tracking", tracking);
		return "fragments/dialogs :: tracking_dialog_body";

	}
}
