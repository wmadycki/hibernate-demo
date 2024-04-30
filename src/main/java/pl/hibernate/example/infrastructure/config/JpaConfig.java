package pl.hibernate.example.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = { "pl.hibernate.example" }
)
public class JpaConfig {
}
