package ch.jaunerc.tichu.backend.app;

import ch.jaunerc.tichu.backend.web.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfiguration.class})
public class TichuBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TichuBackendApplication.class, args);
    }
}
