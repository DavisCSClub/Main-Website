package org.dcsc.core.authentication.user.application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserApplicationRepository extends CrudRepository<UserApplication, Integer> {

    Optional<UserApplication> getUserApplicationByEmail(String email);
}
