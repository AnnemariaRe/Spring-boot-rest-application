package org.annemariare.kotiki.rabbitmq;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.dto.UserDto;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.messaging.ColorResponse;
import org.annemariare.kotiki.messaging.IdResponse;
import org.annemariare.kotiki.messaging.NameResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KotikSender {
    public static final String EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public KotikSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(UserDto user){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, user);
    }

    public void send(KotikDto kotik){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY+"save", kotik);
    }

    public KotikDto send(Long id, String username){
        return (KotikDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY + "id",
                new IdResponse(id, username));
    }

    public KotikDto send(String name, String username){
        return (KotikDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY+"name",
                new NameResponse(name, username));
    }

    public KotikDto send(Color color, String username){
        return (KotikDto) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY+"name",
                new ColorResponse(color, username));
    }

    public void send(Long id){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, id);
    }

    public Object send(String username){
       return rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, username);
    }

}
