package com.nesterrovv.vpn;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor
public class VpnApplication {

    public static void main(String[] args) {
        SpringApplication.run(VpnApplication.class, args);
    }

}
