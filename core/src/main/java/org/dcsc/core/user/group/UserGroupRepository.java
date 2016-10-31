package org.dcsc.core.user.group;

import org.dcsc.core.user.DcscUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {
    UserGroup findByDcscUser(DcscUser dcscUser);

    void deleteByDcscUserAndGroup(DcscUser dcscUser, Group group);
}
