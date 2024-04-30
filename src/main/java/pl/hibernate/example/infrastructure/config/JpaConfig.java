package pl.hibernate.example.infrastructure.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
//        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
        basePackages = { "pl.hibernate.example" }
)
public class JpaConfig {
}
