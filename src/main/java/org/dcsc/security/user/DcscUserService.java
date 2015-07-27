package org.dcsc.security.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DcscUserService {
	@Autowired
	private DcscUserRepository userRepository;

	public DcscUser saveDcscUser(DcscUserForm dcscUserForm) {
		return saveDcscUser(new DcscUser(), dcscUserForm);
	}

	public DcscUser saveDcscUser(long id, DcscUserForm dcscUserForm) {
		Optional<DcscUser> user = userRepository.findUserById(id);

		if(user.isPresent()) {
			return saveDcscUser(user.get(), dcscUserForm);
		}

		return null;
	}

	private DcscUser saveDcscUser(DcscUser dcscUser, DcscUserForm dcscUserForm) {
		String username = dcscUserForm.getUsername();
		String email = dcscUserForm.getEmail();
		String password = new BCryptPasswordEncoder().encode(dcscUserForm.getPassword());
		String name = dcscUserForm.getName();
		Role role = Role.ROLE_USER;

		dcscUser.setUsername(username);
		dcscUser.setName(name);
		dcscUser.setPassword(password);
		dcscUser.setEmail(email);
		dcscUser.setRole(role);

		return userRepository.save(dcscUser);
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
