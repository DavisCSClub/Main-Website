package org.dcsc.core.authentication.membership;

import org.dcsc.core.authentication.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MembershipRepository extends CrudRepository<Membership, Integer> {
    List<Membership> getByUser(User user);
}
