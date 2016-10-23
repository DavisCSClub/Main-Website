package org.dcsc.web.controller;

import org.dcsc.web.constants.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.facebook.api.Event;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class EventController {
    @Autowired(required = false)
    private Facebook facebook;

    @RequestMapping(value = "/event/{id}")
    public ModelAndView event(@PathVariable("id") String id) {
        ModelAndView modelView;

        if (facebook != null) {
            try {
                Event event = retrieveEventFromFacebook(id);
                modelView = new ModelAndView(ViewNames.EVENT, "event", event);
            } catch (ResourceNotFoundException e) {
                modelView = new ModelAndView(new RedirectView("/timeline"));
            }
        } else {
            modelView = new ModelAndView(new RedirectView("/timeline"));
        }

        return modelView;
    }

    private Event retrieveEventFromFacebook(String id) throws ResourceNotFoundException {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.set("fields", "id,name,cover,start_time,end_time,description,place");
        return facebook.fetchObject(id, Event.class, parameters);
    }
}
