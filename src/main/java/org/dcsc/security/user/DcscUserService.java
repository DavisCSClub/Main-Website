package org.dcsc.security.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DcscUserService {
	@Autowired
	private DcscUserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Optional<DcscUser> getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}
}
