package evgesha.blps.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"evgesha.blps.lab1"})
@EnableWebMvc
@EnableSwagger2
public class Lab1Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

}
