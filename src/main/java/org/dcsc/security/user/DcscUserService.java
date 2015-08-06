package org.dcsc.security.user;

import java.util.List;
import java.util.Optional;

import org.dcsc.activity.ActivityService;
import org.dcsc.security.user.form.DcscUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DcscUserService {
	@Autowired
	private DcscUserRepository userRepository;
	@Autowired
	private ActivityService activityService;

    public DcscUser save(DcscUserForm form, long id) {
        Optional<DcscUser> dcscUser = getUserById(id);
        DcscUser user = null;

        if(dcscUser.isPresent()) {
			user = save(form.build(dcscUser.get()));

        }

        return user;
    }

    public DcscUser save(DcscUserForm form) {
		return save(form.build());
    }

	private DcscUser save(DcscUser user) {
		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public List<DcscUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<DcscUser> getUserById(long id) {
		return userRepository.findUserById(id);
	}

	@Transactional(readOnly = true)
	public Optional<DcscUser> getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
}
