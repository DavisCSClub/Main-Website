package org.dcsc.core.authentication.membership;

import org.dcsc.core.authentication.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MembershipRepository extends CrudRepository<Membership, Integer> {
    List<Membership> getByUser(User user);

    @Query("SELECT m FROM Membership m WHERE m.group.id = :groupId AND EXTRACT(year FROM m.startDate) = :year")
    List<Membership> getByGroupAndYear(@Param("groupId") int groupId, @Param("year") int year);
    
    @Query("SELECT DISTINCT EXTRACT(year from startDate) FROM Membership m WHERE m.group.id = :groupId")
    List<Integer> getMembershipYears(@Param("groupId") int groupId);
}
