package org.dcsc.security.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dcsc_users", schema = "dcsc")
public class DcscUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "is_enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "is_locked", nullable = false)
	private boolean locked;
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public DcscUser() {
	}

	public DcscUser(long id, String username, String email, String name, String password, boolean enabled,
			boolean locked, Role role) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
		this.locked = locked;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
