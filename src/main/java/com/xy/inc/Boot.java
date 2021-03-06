package com.xy.inc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 *
 * @author Matheus Xavier
 */
@SpringBootApplication(scanBasePackages = {"com.xy.inc"})
public class Boot extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Boot.class);
    }
}
