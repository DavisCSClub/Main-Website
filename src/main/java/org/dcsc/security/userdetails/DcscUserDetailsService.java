package org.dcsc.security.userdetails;

import java.util.Optional;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DcscUserDetailsService implements UserDetailsService {
	@Autowired
	private DcscUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<DcscUser> user = userService.getUserByUsername(username);

		if(!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Username %s was not found.", username));
		}
		
		return new DcscUserDetails(user.get());
	}
}
