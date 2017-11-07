package main.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by GHAITH on 03/11/2017.
 */
@Configuration
@PropertySource("classpath:/main/resources/app-dev.properties")
@Profile("dev")
public class DataSourceDevConfig {

    @Autowired
    public Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSourceForDev() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setName(env.getProperty("db.name"))
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript(env.getProperty("db.schema"))
                .addScript(env.getProperty("db.insert-schema"))
                .build();
    }
}
