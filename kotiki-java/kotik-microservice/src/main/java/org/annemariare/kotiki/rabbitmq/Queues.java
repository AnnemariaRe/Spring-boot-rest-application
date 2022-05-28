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
    Queue cat_id_queue() {
        return new Queue("cat_id", false);
    }

    @Bean
    Queue cat_name_queue() {
        return new Queue("cat_name", false);
    }

    @Bean
    Queue cat_save_queue() {
        return new Queue("cat_save", false);
    }

    @Bean
    Queue cat_color_queue() {
        return new Queue("cat_color", false);
    }

    @Bean
    Queue cat_delete_queue() {
        return new Queue("cat_delete", false);
    }

    @Bean
    Queue cat_all_queue() {
        return new Queue("cat_all", false);
    }

    @Bean
    Binding binding_id(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY+"id");
    }

    @Bean
    Binding binding_name(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY+"name");
    }

    @Bean
    Binding binding_save(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY+"save");
    }

    @Bean
    Binding binding_color(Queue queue3, TopicExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(ROUTING_KEY+"color");
    }

    @Bean
    Binding binding_delete(Queue queue4, TopicExchange exchange) {
        return BindingBuilder.bind(queue4).to(exchange).with(ROUTING_KEY+"delete");
    }

    @Bean
    Binding binding_all(Queue queue5, TopicExchange exchange) {
        return BindingBuilder.bind(queue5).to(exchange).with(ROUTING_KEY+"all");
    }


}
