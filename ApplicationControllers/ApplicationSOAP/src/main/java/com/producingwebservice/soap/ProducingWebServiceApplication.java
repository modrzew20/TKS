package com.producingwebservice.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.producingwebservice.soap.soapAdapters",
        "com.producingwebservice.soap.soapConverters",
        "com.producingwebservice.soap.soapEndpoints",
        "com.producingwebservice.soap.soapmodel",
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
