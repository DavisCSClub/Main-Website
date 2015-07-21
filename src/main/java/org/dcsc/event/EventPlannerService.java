package org.dcsc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tktong on 7/20/2015.
 */
@Service
public class EventPlannerService {
    @Autowired
    private EventPlannerRepository eventPlannerRepository;

    @Transactional(readOnly = true)
    public List<EventPlanner> getAllEventPlanners() {
        return eventPlannerRepository.findAll();
    }
}
