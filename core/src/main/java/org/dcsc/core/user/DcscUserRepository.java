package org.dcsc.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DcscUserRepository extends JpaRepository<DcscUser, Long> {
    Optional<DcscUser> findUserById(long id);

    Optional<DcscUser> findUserByUsername(String username);

    DcscUser findUserByOidcId(String oidcId);
}
