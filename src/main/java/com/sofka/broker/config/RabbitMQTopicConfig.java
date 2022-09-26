package com.sofka.broker.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class RabbitMQTopicConfig {

    @Bean
    Queue apto101Queue() {
        return new Queue("apto101", false);
    }

    @Bean
    Queue apto102Queue() {
        return new Queue("apto102", false);
    }

    @Bean
    Queue apto201Queue() {
        return new Queue("apto201", false);
    }

    @Bean
    Queue apto202Queue() {
        return new Queue("apto202", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Declarables bindings101(@Value("${spring.rabbitmq.keys101}") List<String> keys, TopicExchange topicExchange, Queue apto101Queue) {
        return new Declarables(keys.stream()
                .map(key -> BindingBuilder.bind(apto101Queue)
                        .to(topicExchange)
                        .with(key))
                .collect(Collectors.toList()));
    }

    @Bean
    Declarables bindings102(@Value("${spring.rabbitmq.keys102}") List<String> keys, TopicExchange topicExchange, Queue apto102Queue) {
        return new Declarables(keys.stream()
                .map(key -> BindingBuilder.bind(apto102Queue)
                        .to(topicExchange)
                        .with(key))
                .collect(Collectors.toList()));
    }

    @Bean
    Declarables bindings201(@Value("${spring.rabbitmq.keys201}") List<String> keys, TopicExchange topicExchange, Queue apto201Queue) {
        return new Declarables(keys.stream()
                .map(key -> BindingBuilder.bind(apto201Queue)
                        .to(topicExchange)
                        .with(key))
                .collect(Collectors.toList()));
    }

    @Bean
    Declarables bindings202(@Value("${spring.rabbitmq.keys202}") List<String> keys, TopicExchange topicExchange, Queue apto202Queue) {
        return new Declarables(keys.stream()
                .map(key -> BindingBuilder.bind(apto202Queue)
                        .to(topicExchange)
                        .with(key))
                .collect(Collectors.toList()));
    }

    @Bean
    public MessageConverter jsonMessageConvert() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConvert());
        return rabbitTemplate;
    }
}
