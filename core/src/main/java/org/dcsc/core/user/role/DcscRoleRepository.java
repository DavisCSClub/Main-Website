package org.dcsc.core.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcscRoleRepository extends JpaRepository<DcscRole, Long> {
}
