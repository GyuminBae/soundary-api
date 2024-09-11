package io.github.eappezo.soundary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SoundaryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoundaryApiApplication.class, args);
    }
}
