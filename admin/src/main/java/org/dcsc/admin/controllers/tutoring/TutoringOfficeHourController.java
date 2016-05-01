package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.QueueSummary;
import org.dcsc.admin.dto.TutorQueueRefreshSummary;
import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Deprecated
@Controller
public class TutoringOfficeHourController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AcademicTermService academicTermService;

    @MessageMapping("/tutor/queue/refresh")
    @SendToUser("/queue/office-hour")
    public TutorQueueRefreshSummary courseQueueRefresh(Authentication authentication) throws Exception {
        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser dcscUser = userDetails.getUser();

        Tutor tutor = tutorService.getTutor(dcscUser);

        // Demo code
        List<QueueSummary> summaries = new ArrayList<>();
        List<AcademicCourse> courses = tutor.getCurrentTermCourses();
        for (AcademicCourse course : courses) {
            QueueSummary summary = new QueueSummary(course.getCode(), ThreadLocalRandom.current().nextInt(0, 19));
            summaries.add(summary);
        }

        return new TutorQueueRefreshSummary(summaries);
    }
}
