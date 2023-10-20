package com.nesterrovv.vpn;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    private Application() {}

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        System.out.println("Initial commit");
    }

}
