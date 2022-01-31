package com.revature.amsapi.config;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class InitConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            // Roles
            Role customer = new Role(1, "Customer");
            Role manager = new Role(2, "Manager");

            roleRepository.saveAll(Arrays.asList(customer, manager));
        };
    }
}
