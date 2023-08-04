package ch.jaunerc.tichu.backend.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = {"ch.jaunerc.tichu.backend.persistence"})
@EntityScan("ch.jaunerc.tichu.backend.persistence")
public class PersistenceConfiguration {
}
