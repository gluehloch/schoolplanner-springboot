package de.awtools.schoolplanner.security;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Register a new user. Do it with this service.
 * 
 * @author winkler
 */
@Service
public class RegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    @Autowired
    private TimeService timeService;

    @Transactional
    public UserRegistration registerNewUserAccount(String username, String email,
            String password, String passwordConfirm) {

        if (!StringUtils.equals(password, passwordConfirm)) {
            throw new IllegalArgumentException("The passwords are not equal.");
        }

        LocalDateTime now = timeService.now();

        UserRegistration user = new UserRegistration();
        user.setUsername(username);
        user.setPassword(new Password(passwordEncoder.encode(password)));
        user.setEmail(email);
        user.setCreated(now);
        user.setToken("TODO_TOKEN");
        /*
        user.setCredentialExpired(false);
        user.setEnabled(true);
        user.setLastChange(now);
        user.setLocked(false);
        */

        userRegisterRepository.save(user);

        return user;
    }


    @Autowired
    private SchoolUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
