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

    @Bean
    CommandLineRunner commandLineRunner(KotikService service) {
        return args -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse ( "2019-12-31" );
            Date date2 = format.parse ( "2001-6-5" );

            var owner = new OwnerEntity(1L, "Annemaria", date2, new ArrayList<>());
            var kotik1 = new KotikEntity(1L, "Gosha", date, "american", Color.RED, owner);
            var kotik2 = new KotikEntity(2L, "Pasha", date, "russian", Color.BROWN, owner);
            kotik1.setFriend(kotik2);
            kotik2.setFriend(kotik1);

            owner.setKotik(kotik1);
            service.add(KotikDto.entityToDto(kotik1));
        };
    }
}
