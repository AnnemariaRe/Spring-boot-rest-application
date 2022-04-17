package org.annemariare.kotiki;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.service.KotikService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication(scanBasePackages = {"org.annemariare.kotiki"})
@EnableJpaRepositories
public class KotikiApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(KotikiApplication.class, args);
    }
    
}
