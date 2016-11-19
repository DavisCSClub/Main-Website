package org.dcsc.core.authentication.membership;

import org.dcsc.core.authentication.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
interface MembershipRepository extends CrudRepository<Membership, Integer> {
    List<Membership> getByUser(User user);

    @Query("SELECT m FROM Membership m WHERE m.group.id = :groupId AND m.startDate BETWEEN :startDate AND :endDate")
    List<Membership> getByGroupBetweenDates(@Param("groupId") int groupId,
                                            @Param("startDate") ZonedDateTime startDate,
                                            @Param("endDate") ZonedDateTime endDate);

    @Query("SELECT DISTINCT EXTRACT(year from endDate) FROM Membership m WHERE m.group.id = :groupId ORDER BY EXTRACT(year from endDate) ASC")
    List<Integer> getMembershipYears(@Param("groupId") int groupId);
}
