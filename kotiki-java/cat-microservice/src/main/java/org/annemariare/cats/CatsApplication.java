package org.annemariare.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.annemariare.cats"})
@EnableJpaRepositories
public class CatsApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(CatsApplication.class, args);
    }

}
