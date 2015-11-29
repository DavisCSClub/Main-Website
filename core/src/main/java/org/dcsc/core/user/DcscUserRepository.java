package org.dcsc.core.user;

import java.util.Optional;

import org.dcsc.core.user.DcscUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcscUserRepository extends JpaRepository<DcscUser, Long> {
	Optional<DcscUser> findUserById(long id);
	Optional<DcscUser> findUserByUsername(String username);
}
