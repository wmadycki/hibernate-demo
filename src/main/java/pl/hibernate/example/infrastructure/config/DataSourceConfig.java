package pl.hibernate.example.infrastructure.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final DataSourceBuilder datasourceBuilder;

    @Bean
    @Primary
    public DataSource dataSource() {
        return datasourceBuilder.build();
    }
}