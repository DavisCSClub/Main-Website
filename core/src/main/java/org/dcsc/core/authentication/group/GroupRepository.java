package org.dcsc.core.authentication.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("newGroupRepository")
interface GroupRepository extends CrudRepository<Group, Integer> {
}
