package pl.hibernate.example.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DataSourceBuilder {

    private final DataSourceProperties datasourceProperties;

    @Value("${p6spy.show-sql:false}")
    private boolean showSql;

    @Value("${spring.datasource.maximum-pool-size:10}")
    private int maxPoolSize;

    public DataSource build() {
        HikariDataSource ds = (HikariDataSource) datasourceProperties.initializeDataSourceBuilder().build();
        ds.setMaximumPoolSize(maxPoolSize);
        checkP6spyUrl(ds);
        return ds;
    }

    private void checkP6spyUrl(HikariDataSource ds) {
        if (!showSql && ds.getJdbcUrl().contains("p6spy")) {
            ds.setJdbcUrl(ds.getJdbcUrl().replace("p6spy:", ""));
        }
        if (ds.getJdbcUrl().contains("p6spy")) {
            ds.setDriverClassName(com.p6spy.engine.spy.P6SpyDriver.class.getName());
        } else {
            ds.setDriverClassName(org.postgresql.Driver.class.getName());
        }
    }

}