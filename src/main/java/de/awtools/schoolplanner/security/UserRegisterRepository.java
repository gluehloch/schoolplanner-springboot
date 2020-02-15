package de.awtools.schoolplanner.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository
        extends CrudRepository<UserRegistration, Long> {

    UserRegistration findByUsername(String username);

}
