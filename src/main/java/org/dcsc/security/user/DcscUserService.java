package org.dcsc.security.user;

import java.util.List;
import java.util.Optional;

import javassist.NotFoundException;
import org.dcsc.security.user.form.DcscUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DcscUserService {
	@Autowired
	private DcscUserRepository userRepository;

    public DcscUser save(DcscUserForm form, long id) throws NotFoundException {
        DcscUser user = userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User #%d does not exists.", id)));

        return save(form.build(user));
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
