package com.mbr.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.mbr.upload")
@EnableTransactionManagement
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
