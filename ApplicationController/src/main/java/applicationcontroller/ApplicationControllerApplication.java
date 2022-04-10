package applicationcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(scanBasePackages = {
        "applicationcontroller",
        "service",
        "repository",
        "model",
        "adapters",
        "Port.In",
        "Port.Out",
        "converters"
})
//@SpringBootApplication
public class ApplicationControllerApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
//        applicationContext =
//                new AnnotationConfigApplicationContext(ApplicationControllerApplication.class);
//
//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }
        SpringApplication.run(ApplicationControllerApplication.class, args);
    }

}
