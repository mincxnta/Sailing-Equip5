package cat.institutmarianao.sailing.ws.service;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.BookedPlace;

public interface BookedPlaceService {
	List<BookedPlace> findByTripIdAndDate(Long id, Date date);
}
