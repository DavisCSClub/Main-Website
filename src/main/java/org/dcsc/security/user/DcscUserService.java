package org.dcsc.security.user;

import java.util.List;
import java.util.Optional;

import org.dcsc.security.user.form.DcscUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DcscUserService {
	@Autowired
	private DcscUserRepository userRepository;

    public DcscUser save(DcscUserForm form, long id) {
        Optional<DcscUser> dcscUser = getUserById(id);
        DcscUser user = null;

        if(dcscUser.isPresent()) {
            user = userRepository.save(form.build(dcscUser.get()));
        }

        return null;
    }

    public DcscUser save(DcscUserForm form) {
        return userRepository.save(form.build());
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
