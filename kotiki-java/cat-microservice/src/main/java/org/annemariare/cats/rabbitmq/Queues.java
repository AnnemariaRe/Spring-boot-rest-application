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
    Queue catIdQueue() {
        return new Queue("cat_id", false);
    }

    @Bean
    Queue catNameQueue() {
        return new Queue("cat_name", false);
    }

    @Bean
    Queue catSaveQueue() {
        return new Queue("cat_save", false);
    }

    @Bean
    Queue catColorQueue() {
        return new Queue("cat_color", false);
    }

    @Bean
    Queue catDeleteQueue() {
        return new Queue("cat_delete", false);
    }

    @Bean
    Queue catAllQueue() {
        return new Queue("cat_all", false);
    }

    @Bean
    Binding bindingId(Queue catIdQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catIdQueue).to(exchange).with(ROUTING_KEY+"id");
    }

    @Bean
    Binding bindingName(Queue catNameQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catNameQueue).to(exchange).with(ROUTING_KEY+"name");
    }

    @Bean
    Binding bindingSave(Queue catSaveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catSaveQueue).to(exchange).with(ROUTING_KEY+"save");
    }

    @Bean
    Binding bindingColor(Queue catColorQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catColorQueue).to(exchange).with(ROUTING_KEY+"color");
    }

    @Bean
    Binding bindingDelete(Queue catDeleteQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catDeleteQueue).to(exchange).with(ROUTING_KEY+"delete");
    }

    @Bean
    Binding bindingAll(Queue catAllQueue, TopicExchange exchange) {
        return BindingBuilder.bind(catAllQueue).to(exchange).with(ROUTING_KEY+"all");
    }

}
