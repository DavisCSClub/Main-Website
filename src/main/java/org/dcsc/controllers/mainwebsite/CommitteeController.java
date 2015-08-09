package org.dcsc.controllers.mainwebsite;

import org.dcsc.committee.Committee;
import org.dcsc.committee.ReadOnlyCommitteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by tktong on 7/10/2015.
 */
@Controller
@RequestMapping(value = "/committee")
public class CommitteeController {
    @Autowired
    private ReadOnlyCommitteeService committeeService;

    @RequestMapping(value = "/{tag}")
    public String tutoring(@PathVariable("tag") String tag, Model model) {
        Optional<Committee> committee = committeeService.getCommitteeByTag(tag);

        if(!committee.isPresent()) {
            return "redirect:/error/404";
        }

        Committee c = committee.get();

        if(c.getBannerImagePath() == null) {
            c.setBannerImagePath("/img/placeholder/placeholder-1920-700.jpg");
        }

        model.addAttribute("committee",c);

        return "main/committee";
    }
}
