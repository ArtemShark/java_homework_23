package com.company.homework_lesson_23;

import com.company.homework_lesson_23.utils.RentInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class AppStarter {
    private final RentInitializer rentInitializer;

    @Bean
    public ApplicationRunner init() {
        log.debug("Application is running!");

        return args -> {
            rentInitializer.initDB();
        };
    }
}
