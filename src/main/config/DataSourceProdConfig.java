package main.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by GHAITH on 03/11/2017.
 */
@Configuration
@PropertySource("classpath:/main/resources/app-prod.properties")
@Profile("prod")
public class DataSourceProdConfig {

    @Value("${db.driver}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    public DataSource dataSourceForProd() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
