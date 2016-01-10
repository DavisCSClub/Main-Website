package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.dto.TutorManagementChangeSet;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdminTutoringManagementRestController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(value = "/admin/r/tutoring/management", method = RequestMethod.PUT)
    public RestTransactionResult tutorManagementPage(@RequestBody TutorManagementChangeSet changeSet) {
        RestTransactionResult result = null;

        int userId = changeSet.getUserId();
        Optional<DcscUser> userWrapper = dcscUserService.getUserById(changeSet.getUserId());

        if (userWrapper.isPresent()) {
            DcscUser user = userWrapper.get();

            if (changeSet.isRemove()) {
                result = removeUserFromTutoring(user);
            } else {
                result = addUserToTutoring(user);
            }
        } else {
            result = RestTransactionResult.fail("Failed to find user with id " + userId);
        }

        return result;
    }

    private RestTransactionResult addUserToTutoring(DcscUser user) {
        RestTransactionResult result;

        if (user.inGroup("Tutoring Committee")) {
            result = RestTransactionResult.fail("User already in tutoring committee.");
        } else {
            try {
                // Add user group
                tutorService.addUserToTutoring(user, false);
                // Add or re-enable tutor account
                Tutor tutor = tutorService.getTutor(user);

                if (tutor == null) { // user never had a tutor account
                    tutor = new Tutor(user);
                } else {
                    tutor.setActive(true);
                }

                tutorService.save(tutor);

                result = RestTransactionResult.success("User " + user.getId() + " successfully added to tutoring committee.");
            } catch (Exception e) {
                e.printStackTrace();
                result = RestTransactionResult.fail("Failed to add group - Tutoring Committee - to user.");
            }
        }

        return result;
    }

    private RestTransactionResult removeUserFromTutoring(DcscUser user) {
        RestTransactionResult result;

        if (user.inGroup("Tutoring Committee")) {
            try {
                tutorService.removeUserFromTutoring(user);
                Tutor tutor = tutorService.getTutor(user);

                if (tutor == null) {
                    return RestTransactionResult.fail("User found in tutoring committee without tutor account.");
                } else {
                    tutor.setActive(false);
                }

                tutorService.save(tutor);

                result = RestTransactionResult.success("User " + user.getId() + " successfully removed from tutoring committee.");
            } catch (Exception e) {
                e.printStackTrace();
                result = RestTransactionResult.fail("Failed to add group - Tutoring Committee - to user.");
            }
        } else {
            result = RestTransactionResult.fail("User was never part of tutoring committee.");
        }

        return result;
    }
}
