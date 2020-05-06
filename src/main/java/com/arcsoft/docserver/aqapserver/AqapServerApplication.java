package com.arcsoft.docserver.aqapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xqh3622
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.arcsoft.docserver"})
public class AqapServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AqapServerApplication.class, args);
    }

}
