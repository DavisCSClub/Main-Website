package org.dcsc.web.officer;

import org.dcsc.core.authentication.membership.Membership;
import org.dcsc.core.authentication.membership.MembershipComparator;
import org.dcsc.core.authentication.membership.MembershipService;
import org.dcsc.web.constants.ModelAttributeNames;
import org.dcsc.web.constants.ViewNames;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

@Controller
public class OfficerController {
    private static final Comparator<Membership> TITLE_COMPARATOR = new MembershipComparator();
    private static final int OFFICER_BOARD_GROUP_ID = 2;

    @Autowired
    private MembershipService membershipService;

    @RequestMapping(value = "/officers")
    public String officers(@RequestParam(value = "year", defaultValue = "0") int year, Model model) {
        year = (year == 0) ? ZonedDateTime.now().getYear() : year;

        List<Membership> officers = membershipService.getByGroupAndAcademicYear(OFFICER_BOARD_GROUP_ID, year);
        List<Integer> years = membershipService.getMembershipYears(OFFICER_BOARD_GROUP_ID);
        officers.sort(TITLE_COMPARATOR);

        model.addAttribute("startYear", year);
        model.addAttribute("endYear", year + 1);
        model.addAttribute(ModelAttributeNames.OFFICERS, officers);
        model.addAttribute(ModelAttributeNames.YEARS, years);

        return ViewNames.OFFICERS;
    }

    @RequestMapping(value = "/officers/{id}")
    public String officer(@PathVariable("id") int userId, Model model) {
        List<Membership> memberships = membershipService.getByUser(userId);
        if (memberships.isEmpty() || !isOfficer(memberships)) {
            return "redirect:/officers";
        }

        model.addAttribute("user", memberships.get(0).getUser());
        model.addAttribute("memberships", memberships);

        return "main/profile";
    }

    private boolean isOfficer(List<Membership> memberships) {
        return memberships.stream().anyMatch(membership -> membership.getGroup().getId() == OFFICER_BOARD_GROUP_ID);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/officers";
    }
}