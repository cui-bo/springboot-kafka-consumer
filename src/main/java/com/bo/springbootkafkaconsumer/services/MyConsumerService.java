package com.bo.springbootkafkaconsumer.services;

import com.bo.springbootkafkaconsumer.models.MyModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyConsumerService {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(MyModel myModel) {
        log.info("Received  : " + myModel);
    }
}