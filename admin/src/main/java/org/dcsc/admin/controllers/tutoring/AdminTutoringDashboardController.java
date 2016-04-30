package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.constants.ViewNames;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Deprecated
@Controller
public class AdminTutoringDashboardController {
    @RequestMapping("/admin/tutoring")
    @PreAuthorize("hasGroup('Tutoring Committee')")
    public String getTutoringPage() {
        return ViewNames.ADMIN_TUTORING_DASHBOARD;
    }
}
