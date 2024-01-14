package com.authentificationservice.service;

// Assuming you have a KafkaListenerContainerFactory bean named "kafkaListenerContainerFactory" in your configuration.

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;

public class AuthenticationListener {

    private final CountDownLatch latch = new CountDownLatch(1);
    private boolean isAuthenticated;

    @KafkaListener(topics = "tnb-authentication-result", groupId = "authentication-service", containerFactory = "kafkaListenerContainerFactory")
    public void listenToAuthenticationResult(@Payload Boolean result) {
        isAuthenticated = result;
        latch.countDown();
    }

    public boolean waitForAuthenticationResult() throws InterruptedException {
        latch.await(); // Wait until the result is received
        return isAuthenticated;
    }
}

