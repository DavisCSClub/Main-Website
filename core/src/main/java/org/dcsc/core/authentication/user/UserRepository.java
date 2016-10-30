package org.dcsc.core.authentication.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("newUserRepository")
interface UserRepository extends CrudRepository<User, Integer> {
    User getUserByEmail(String email);

    User getUserByOpenIdIdentifier(String openIdIdentifier);
}
