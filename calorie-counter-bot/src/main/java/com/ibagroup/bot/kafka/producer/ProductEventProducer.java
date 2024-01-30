package com.ibagroup.bot.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventProducer {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendProduct(ProductRegistrationDto product) throws JsonProcessingException {
        String key = product.getName();
        String value = objectMapper.writeValueAsString(product);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        var completableFuture = kafkaTemplate.send(record);
        completableFuture.whenComplete((sendResult, throwable) -> {
            if(throwable == null){
                handleSuccess(key, value, sendResult);
            } else {
                handleFailure(key, value, throwable);
            }
        });
    }

    private void handleSuccess(String key, String value, SendResult<String, String> sendResult) {
        log.info("Message sent successfully for the key: {} and the value: {}, partition is {}", key, value, sendResult.getRecordMetadata().partition());
    }

    private void handleFailure(String key, String value, Throwable throwable) {
        log.error("Error sending message and the exception is {}", throwable.getMessage(), throwable);
    }

}
