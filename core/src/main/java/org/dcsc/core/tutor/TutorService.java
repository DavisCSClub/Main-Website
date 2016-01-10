package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.course.AcademicCourseService;
import org.dcsc.core.time.AcademicTerm;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.group.GroupService;
import org.dcsc.core.user.group.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private TutorRelationRepository tutorRelationRepository;

    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private AcademicCourseService academicCourseService;
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserGroupService userGroupService;

    @Transactional(readOnly = true)
    public Tutor getTutor(Authentication authentication) {
        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser dcscUser = userDetails.getUser();

        return getTutor(dcscUser);
    }

    @Transactional(readOnly = true)
    public Tutor getTutor(DcscUser dcscUser) {
        return tutorRepository.findByDcscUser(dcscUser);
    }

    @Transactional(readOnly = true)
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    public void updateCoursesTutored(Authentication authentication, List<String> newTutoredCourses) throws Exception {
        Tutor tutor = getTutor(authentication);
        List<AcademicCourse> courses = new ArrayList<>();

        newTutoredCourses.forEach(course -> courses.add(academicCourseService.get(course)));

        updateCoursesTutored(tutor, courses);
    }

    public void updateCoursesTutored(Tutor tutor, List<AcademicCourse> newTutoredCourses) throws Exception {
        AcademicTerm currentTerm = academicTermService.getCurrentTerm();

        // Find courses that have been removed
        List<AcademicCourse> existingCourses = tutor.getCourses(currentTerm.getCode());
        List<AcademicCourse> coursesToRemove = existingCourses.stream()
                .filter(course -> !newTutoredCourses.contains(course)).collect(Collectors.toList());

        // Remove any courses that have not changed
        newTutoredCourses.removeIf(course -> existingCourses.contains(course));

        for (AcademicCourse course : coursesToRemove) {
            tutor.removeAcademicCourse(course);
            tutorRelationRepository.deleteByTutorAndAcademicCourseAndAcademicTerm(tutor, course, currentTerm);
        }

        // Add courses to tutor
        for (AcademicCourse course : newTutoredCourses) {
            TutorRelation tutorRelation = new TutorRelation(tutor, academicCourseService.get(course.getCode()), currentTerm);
            tutor.addTutorRelation(tutorRelation);
        }

        save(tutor);
    }

    public void addUserToTutoring(DcscUser dcscUser, boolean isAdmin) throws Exception {
        dcscUser.addGroup(groupService.getGroup("Tutoring Committee"), isAdmin);
        dcscUserService.save(dcscUser);
    }

    public void removeUserFromTutoring(DcscUser dcscUser) throws Exception {
        userGroupService.removeUserGroup(dcscUser, "Tutoring Committee");
    }

    public void save(Tutor tutor) {
        tutorRepository.save(tutor);
    }
}
