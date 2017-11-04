package main.config;
import main.repository.AccountRepository;
import main.repository.JdbcAccountRepository;
import main.services.TransferService;
import main.services.TransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Created by GHAITH on 30/10/2017.
 */

@Configuration
@Import({DataSourceDevConfig.class,DataSourceProdConfig.class})
public class ApplicationConfig {

    @Autowired
    DataSource dataSource;

    @Bean(name = "transferService")
    public TransferService transferService() {
        return new TransferServiceImpl(accountRepository());
    }

    @Bean(name = "accountRepository")
    public AccountRepository accountRepository() {
        return new JdbcAccountRepository(dataSource);
    }
}


