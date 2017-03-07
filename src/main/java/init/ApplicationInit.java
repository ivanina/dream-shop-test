package init;

import dao.AddressManager;
import dao.CustomerManager;
import dao.OrderManger;
import dao.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages = {
        "controller", "dao", "service","configuration"
})
public class ApplicationInit {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationInit.class, args);

    }
}
