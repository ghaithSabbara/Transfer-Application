import main.config.ApplicationConfig;
import main.services.TransferService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by GHAITH on 03/11/2017.
 */

public class TransferServiceTest {
    private TransferService transferService;

    @Before
    public void setUp() {
        /* Set Prod Profile active*/
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        // Create The application from the configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // Look up the application service interface
        transferService = (TransferService) context.getBean("transferService");
    }

    /* Dev profile test*/
    @Test
    public void loadAccount() {
        String balance = transferService.testLoadAccount(1);
        Assert.assertEquals(balance, "1000");
    }

    /* Prod profile test*/
    @Test
    public void debit() {
        Double newBalance = transferService.debit(300.00, 1);
        Assert.assertEquals(newBalance, 700.0, 0.001);
    }

    /* Prod profile test*/
    @Test
    public void monetaryAmount() {
        transferService.monetaryAmount(300.00, 2, 1);
    }
}
