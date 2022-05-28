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
    Queue queue() {
        return new Queue("cat_id", false);
    }

    @Bean
    Queue queue1() {
        return new Queue("cat_name", false);
    }

    @Bean
    Queue queue2() {
        return new Queue("cat_save", false);
    }

    @Bean
    Queue queue3() {
        return new Queue("cat_color", false);
    }

    @Bean
    Queue queue4() {
        return new Queue("cat_delete", false);
    }

    @Bean
    Queue queue5() {
        return new Queue("cat_all", false);
    }

    @Bean
    Queue queue6() {
        return new Queue("owner_save", false);
    }

    @Bean
    Queue queue7() {
        return new Queue("owner_id", false);
    }

    @Bean
    Queue queue8() {
        return new Queue("owner_name", false);
    }

    @Bean
    Queue queue9() {
        return new Queue("owner_delete", false);
    }

    @Bean
    Queue queue10() {
        return new Queue("owner_all", false);
    }

    @Bean
    Queue queue11() {
        return new Queue("owner_kotiki", false);
    }

    @Bean
    Binding binding1(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY+"id");
    }

    @Bean
    Binding binding2(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY+"name");
    }

    @Bean
    Binding binding3(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY+"save");
    }

    @Bean
    Binding binding4(Queue queue3, TopicExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(ROUTING_KEY+"color");
    }

    @Bean
    Binding binding5(Queue queue4, TopicExchange exchange) {
        return BindingBuilder.bind(queue4).to(exchange).with(ROUTING_KEY+"delete");
    }

    @Bean
    Binding binding6(Queue queue5, TopicExchange exchange) {
        return BindingBuilder.bind(queue5).to(exchange).with(ROUTING_KEY+"all");
    }

    @Bean
    Binding binding7(Queue queue6, TopicExchange exchange) {
        return BindingBuilder.bind(queue6).to(exchange).with(ROUTING_KEY+"o_save");
    }

    @Bean
    Binding binding8(Queue queue7, TopicExchange exchange) {
        return BindingBuilder.bind(queue7).to(exchange).with(ROUTING_KEY+"o_id");
    }

    @Bean
    Binding binding9(Queue queue8, TopicExchange exchange) {
        return BindingBuilder.bind(queue8).to(exchange).with(ROUTING_KEY+"o_name");
    }

    @Bean
    Binding binding10(Queue queue9, TopicExchange exchange) {
        return BindingBuilder.bind(queue9).to(exchange).with(ROUTING_KEY+"o_delete");
    }

    @Bean
    Binding binding11(Queue queue10, TopicExchange exchange) {
        return BindingBuilder.bind(queue10).to(exchange).with(ROUTING_KEY+"o_all");
    }

    @Bean
    Binding binding12(Queue queue11, TopicExchange exchange) {
        return BindingBuilder.bind(queue11).to(exchange).with(ROUTING_KEY+"o_kotiki");
    }

}
