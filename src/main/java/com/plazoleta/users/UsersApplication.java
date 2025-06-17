package com.plazoleta.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Bean
    public CommandLineRunner inspectBeans(ApplicationContext context) {
        return args -> {
            System.out.println("🔍 Beans registrados en ApplicationContext:");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("✅ " + beanName);
            }
        };
    }

}
