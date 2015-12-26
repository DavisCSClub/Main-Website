package org.dcsc.core.version;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildVersionRepository extends JpaRepository<BuildVersion, String> {
}
