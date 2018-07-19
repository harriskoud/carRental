/*package com.carRental.carRentalApp;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

import com.carRental.carRentalApp.rabbitMq.Receiver;

import lombok.RequiredArgsConstructor;

@Component
//@Slf4j
@RequiredArgsConstructor
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class SendMessage implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public SendMessage(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(CarRentalAppApplication.topicExchangeName, "koud.baz", "Hello from RabbitMQ!");
        receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}*/