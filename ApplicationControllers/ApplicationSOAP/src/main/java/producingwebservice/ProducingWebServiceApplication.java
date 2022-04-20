package producingwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "producingwebservice.soapAdapters",
        "producingwebservice.soapConverters",
        "producingwebservice.soapEndpoints",
        "service",
        "repository",
        "model",
        "adapters",
        "Port.In",
        "Port.Out",
        "converters",
})
public class ProducingWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducingWebServiceApplication.class,args);
    }
}
