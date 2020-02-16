package de.awtools.schoolplanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("/now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
