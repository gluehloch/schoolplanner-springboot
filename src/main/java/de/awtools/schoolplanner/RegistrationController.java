package de.awtools.schoolplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.awtools.schoolplanner.security.RegistrationService;
import de.awtools.schoolplanner.security.UserRegistration;

@RestController
@RequestMapping("/authentication")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin
    @PostMapping(value = "/register")
    public ConfirmRegistration register(
            @RequestBody UserRegistrationJson userRegistrationJson) {

        UserRegistration newUserAccount = registrationService
                .registerNewUserAccount(
                        userRegistrationJson.getUsername(),
                        userRegistrationJson.getEmail(),
                        userRegistrationJson.getPassword(),
                        userRegistrationJson.getPasswordConfirm());

        // Token generieren und speichern...
        // Diese URL wird per Email an den neuen Benutzer verschickt.

        ConfirmRegistration cr = new ConfirmRegistration("TODO: URL");

        return cr;
    }

    @CrossOrigin
    @PostMapping(value = "/confirmMailAddress")
    public String confirmMailAddress(String token) {
        // TODO registrationService.confirmMailAddress();

        return "TODO";
    }

}
