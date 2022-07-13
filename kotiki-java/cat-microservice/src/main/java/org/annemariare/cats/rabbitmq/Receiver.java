package org.annemariare.cats.rabbitmq;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.messaging.ColorResponse;
import org.annemariare.cats.messaging.IdResponse;
import org.annemariare.cats.messaging.NameResponse;
import org.annemariare.cats.service.CatService;
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
    private CatService service;

    @RabbitListener(queues = "cat_save")
    public void receive(CatDto kotik) {
        service.add(kotik);
    }

    @RabbitListener(queues = "cat_all")
    public List<CatDto> receive(String username) {
        return service.getAll(username);
    }

    @RabbitListener(queues = "cat_id")
    public CatDto receive(@Payload IdResponse data) {
        return service.getOne(data.getId(), data.getUsername());
    }

    @RabbitListener(queues = "cat_name")
    public CatDto receive(@Payload NameResponse data) {
        return service.getSomeByName(data.getName(), data.getUsername());
    }

    @RabbitListener(queues = "cat_color")
    public List<CatDto> receive(@Payload ColorResponse data) {
        return service.getSomeByColor(data.getColor(), data.getUsername());
    }

    @RabbitListener(queues = "cat_delete")
    public void receive(Long id) {
        service.delete(id);
    }

}
