package org.dcsc.model.user.details;

import org.dcsc.model.user.DcscUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class DcscUserDetails extends User {
	private final DcscUser user;
	
	public DcscUserDetails(DcscUser user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		
		this.user = user;
	}

	public DcscUser getUser() {
		return user;
	}

}
