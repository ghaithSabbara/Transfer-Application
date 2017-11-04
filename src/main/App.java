package main;

import main.config.ApplicationConfig;
import main.services.TransferService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by GHAITH on 31/10/2017.
 */

public class App {
    public static void main(String[] args) throws SQLException {
        /*Active Prod  profile*/
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"prod");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TransferService ts1 = (TransferService) context.getBean("transferService");
        //DataSource dataSource = context.getBean(DataSource.class);
        ResultSet rs = ts1.loadAccount(1);
        while (rs.next()) {
            System.out.println("Balance :" + rs.getString("balance"));
        }
    }
}
