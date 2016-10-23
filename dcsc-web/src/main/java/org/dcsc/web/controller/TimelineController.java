package org.dcsc.web.controller;

import org.dcsc.web.constants.ViewNames;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Event;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TimelineController {
    private static final String FACEBOOOK_PAGE_ID = "daviscsclub";
    private static final PagedList<Event> NO_EVENTS = new PagedList<>(new ArrayList<>(), null, null);

    @Autowired(required = false)
    private Facebook facebook;

    @RequestMapping(value = "/timeline")
    public ModelAndView timeline(@RequestParam(value = "size", defaultValue = "5") int size,
                                 @RequestParam(value = "before", required = false) String before,
                                 @RequestParam(value = "after", required = false) String after) {
        ModelAndView modelView = new ModelAndView(ViewNames.TIMELINE);

        if (facebook != null) {
            MultiValueMap<String, String> parameters = buildParametersMap(size, before, after);
            PagedList<Event> events = facebook.fetchConnections(FACEBOOOK_PAGE_ID, "events", Event.class, parameters);

            modelView.addObject("events", events);

            Optional.ofNullable(events.getPreviousPage())
                    .map(PagingParameters::getBefore)
                    .ifPresent(parameter -> modelView.addObject("before", parameter));
            Optional.ofNullable(events.getNextPage())
                    .map(PagingParameters::getAfter)
                    .ifPresent(parameter -> modelView.addObject("after", parameter));
        } else {
            modelView.addObject("events", NO_EVENTS);
        }

        return modelView;
    }

    private MultiValueMap<String, String> buildParametersMap(int size, String before, String after) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.set("limit", Integer.toString(size));
        parameters.set("fields", "id,cover,start_time,end_time,name,place,description");

        if (before != null) {
            parameters.set("before", before);
        } else if (after != null) {
            parameters.set("after", after);
        }

        return parameters;
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/timeline";
    }


}
