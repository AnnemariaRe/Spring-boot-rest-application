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
    Queue owner_save_queue() {
        return new Queue("owner_save", false);
    }

    @Bean
    Queue owner_id_queue() {
        return new Queue("owner_id", false);
    }

    @Bean
    Queue owner_name_queue() {
        return new Queue("owner_name", false);
    }

    @Bean
    Queue owner_delete_queue() {
        return new Queue("owner_delete", false);
    }

    @Bean
    Queue owner_all_queue() {
        return new Queue("owner_all", false);
    }

    @Bean
    Queue owner_kotiki_queue() {
        return new Queue("owner_kotiki", false);
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

    @Bean
    Binding binding_save2(Queue queue6, TopicExchange exchange) {
        return BindingBuilder.bind(queue6).to(exchange).with(ROUTING_KEY+"o_save");
    }

    @Bean
    Binding binding_id2(Queue queue7, TopicExchange exchange) {
        return BindingBuilder.bind(queue7).to(exchange).with(ROUTING_KEY+"o_id");
    }

    @Bean
    Binding binding_name2(Queue queue8, TopicExchange exchange) {
        return BindingBuilder.bind(queue8).to(exchange).with(ROUTING_KEY+"o_name");
    }

    @Bean
    Binding binding_delete2(Queue queue9, TopicExchange exchange) {
        return BindingBuilder.bind(queue9).to(exchange).with(ROUTING_KEY+"o_delete");
    }

    @Bean
    Binding binding_all2(Queue queue10, TopicExchange exchange) {
        return BindingBuilder.bind(queue10).to(exchange).with(ROUTING_KEY+"o_all");
    }

    @Bean
    Binding binding_kotiki(Queue queue11, TopicExchange exchange) {
        return BindingBuilder.bind(queue11).to(exchange).with(ROUTING_KEY+"o_kotiki");
    }

}
