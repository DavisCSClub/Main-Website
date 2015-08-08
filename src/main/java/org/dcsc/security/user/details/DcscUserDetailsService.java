package org.dcsc.security.user.details;

import java.util.Optional;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DcscUserDetailsService implements UserDetailsService {
	@Autowired
	private DcscUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException, DisabledException {
		Optional<DcscUser> user = userService.getUserByUsername(username);

		if(!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Username %s was not found.", username));
		}

		DcscUser dcscUser = user.get();

		if(dcscUser.isLocked()) {
			throw new LockedException(String.format("Account %s is currently locked.", username));
		}
		else if(!dcscUser.isEnabled()) {
			throw new DisabledException(String.format("Account %s is disabled. If this is a mistake, contact the administration.", username));
		}


		return new DcscUserDetails(user.get());
	}
}
