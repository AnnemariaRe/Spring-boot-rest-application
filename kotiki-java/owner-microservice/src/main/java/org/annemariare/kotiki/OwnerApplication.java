package org.annemariare.kotiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.annemariare.kotiki"})
@EnableJpaRepositories
public class OwnerApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(OwnerApplication.class, args);
    }

}
