package applicationcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "applicationcontroller.api",
        "service",
        "repository",
        "model",
        "adapters",
        "Port.In",
        "Port.Out",
        "converters"
})
public class ApplicationControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationControllerApplication.class, args);
    }
}
