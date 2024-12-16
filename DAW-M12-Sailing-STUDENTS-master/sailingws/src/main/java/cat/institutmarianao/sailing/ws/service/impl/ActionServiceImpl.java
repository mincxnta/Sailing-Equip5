package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.repository.BookedPlaceRepository;
import cat.institutmarianao.sailing.ws.service.ActionService;

@Validated
@Service
public class ActionServiceImpl implements ActionService{
	@Autowired
	private ActionRepository actionRepository;

	@Override
	public List<Action> findByTripId(Long id) {
		// TODO Auto-generated method stub
		return actionRepository.findByTripId(id);
	}
	


}
