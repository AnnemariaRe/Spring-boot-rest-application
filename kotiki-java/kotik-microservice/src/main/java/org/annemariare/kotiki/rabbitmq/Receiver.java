package org.annemariare.kotiki.rabbitmq;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.messaging.ColorResponse;
import org.annemariare.kotiki.messaging.IdResponse;
import org.annemariare.kotiki.messaging.NameResponse;
import org.annemariare.kotiki.service.KotikService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableRabbit
public class Receiver {
    @Autowired
    private KotikService service;

    @RabbitListener(queues = "cat_save")
    public void receive(KotikDto kotik) {
        service.add(kotik);
    }

    @RabbitListener(queues = "cat_all")
    public List<KotikDto> receive(String username) {
        return service.getAll(username);
    }

    @RabbitListener(queues = "cat_id")
    public KotikDto receive(@Payload IdResponse data) {
        return service.getOne(data.getId(), data.getUsername());
    }

    @RabbitListener(queues = "cat_name")
    public KotikDto receive(@Payload NameResponse data) {
        return service.getSomeByName(data.getName(), data.getUsername());
    }

    @RabbitListener(queues = "cat_color")
    public List<KotikDto> receive(@Payload ColorResponse data) {
        return service.getSomeByColor(data.getColor(), data.getUsername());
    }

    @RabbitListener(queues = "cat_delete")
    public void receive(Long id) {
        service.delete(id);
    }

}
