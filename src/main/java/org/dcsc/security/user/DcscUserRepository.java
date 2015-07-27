package org.dcsc.security.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcscUserRepository extends JpaRepository<DcscUser, Long> {
	Optional<DcscUser> findUserById(long id);
	Optional<DcscUser> findUserByUsername(String username);
}
