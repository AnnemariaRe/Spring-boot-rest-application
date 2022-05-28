package org.annemariare.kotiki.rabbitmq;

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
    Queue catSavequeue() {
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
    Binding bindingId(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY+"id");
    }

    @Bean
    Binding bindingName(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY+"name");
    }

    @Bean
    Binding bindingSave(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY+"save");
    }

    @Bean
    Binding bindingColor(Queue queue3, TopicExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(ROUTING_KEY+"color");
    }

    @Bean
    Binding bindingDelete(Queue queue4, TopicExchange exchange) {
        return BindingBuilder.bind(queue4).to(exchange).with(ROUTING_KEY+"delete");
    }

    @Bean
    Binding bindingAll(Queue queue5, TopicExchange exchange) {
        return BindingBuilder.bind(queue5).to(exchange).with(ROUTING_KEY+"all");
    }

}
