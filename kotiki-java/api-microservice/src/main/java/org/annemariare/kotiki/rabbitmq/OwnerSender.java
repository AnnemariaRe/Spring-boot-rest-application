package org.annemariare.kotiki.rabbitmq;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.messaging.IdResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerSender {
    public static final String EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OwnerSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(OwnerDto owner){
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY+"o_save", owner);
    }

    public OwnerDto send_id(Long id){
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                    EXCHANGE, ROUTING_KEY + "o_id", id);
    }

    public OwnerDto send(String name) {
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                EXCHANGE, ROUTING_KEY + "o_name", name);
    }

    public void send(Long id) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY+"o_delete", id);
    }

    public List<KotikDto> send(Long id, String username) {
        return (List<KotikDto>) rabbitTemplate.convertSendAndReceive(
                EXCHANGE, ROUTING_KEY + "o_kotiki", new IdResponse(id, username));
    }
}
