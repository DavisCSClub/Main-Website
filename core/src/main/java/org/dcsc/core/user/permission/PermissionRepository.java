package org.dcsc.core.user.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
