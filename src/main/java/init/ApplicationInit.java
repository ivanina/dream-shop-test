package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dao","configuration"})
public class ApplicationInit {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationInit.class, args);

    }
}
