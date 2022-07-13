package org.annemariare.cats.rabbitmq;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.dto.UserDto;
import org.annemariare.cats.enums.Color;
import org.annemariare.cats.messaging.ColorResponse;
import org.annemariare.cats.messaging.IdResponse;
import org.annemariare.cats.messaging.NameResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatSender {
    public static final String EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(UserDto user){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, user);
    }

    public void send(CatDto cat){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY+"save", cat);
    }

    public CatDto send(Long id, String username){
        return (CatDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY + "id",
                new IdResponse(id, username));
    }

    public CatDto send(String name, String username){
        return (CatDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY+"name",
                new NameResponse(name, username));
    }

    public CatDto send(Color color, String username){
        return (CatDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY+"name",
                new ColorResponse(color, username));
    }

    public void send(Long id){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, id);
    }

    public Object send(String username){
       return rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, username);
    }

}
