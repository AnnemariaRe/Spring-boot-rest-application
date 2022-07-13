package org.annemariare.cats.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Queues {
    public static final String ROUTING_KEY = "message_routingKey";

    @Bean
    Queue ownerSaveQueue() {
        return new Queue("owner_save", false);
    }

    @Bean
    Queue ownerIdQueue() {
        return new Queue("owner_id", false);
    }

    @Bean
    Queue ownerNameQueue() {
        return new Queue("owner_name", false);
    }

    @Bean
    Queue ownerDeleteQueue() {
        return new Queue("owner_delete", false);
    }

    @Bean
    Queue ownerAllQueue() {
        return new Queue("owner_all", false);
    }

    @Bean
    Queue ownerCatsQueue() {
        return new Queue("owner_cats", false);
    }

    @Bean
    Binding bindingSave2(Queue ownerSaveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerSaveQueue).to(exchange).with(ROUTING_KEY+"o_save");
    }

    @Bean
    Binding bindingId2(Queue ownerIdQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerIdQueue).to(exchange).with(ROUTING_KEY+"o_id");
    }

    @Bean
    Binding bindingName2(Queue ownerNameQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerNameQueue).to(exchange).with(ROUTING_KEY+"o_name");
    }

    @Bean
    Binding bindingDelete2(Queue ownerDeleteQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerDeleteQueue).to(exchange).with(ROUTING_KEY+"o_delete");
    }

    @Bean
    Binding bindingAll2(Queue ownerAllQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerAllQueue).to(exchange).with(ROUTING_KEY+"o_all");
    }

    @Bean
    Binding bindingCats(Queue ownerCatsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(ownerCatsQueue).to(exchange).with(ROUTING_KEY+"o_cats");
    }
}
