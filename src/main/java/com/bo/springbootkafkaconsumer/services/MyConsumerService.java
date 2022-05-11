package com.bo.springbootkafkaconsumer.services;

import com.bo.springbootkafkaconsumer.models.MyModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class MyConsumerService {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumer.group}")
    public void listen(MyModel myModel) {
        log.info("Received model {} with id {} at {}" ,
                myModel.getModelName(), myModel.getModelId(), new Timestamp(System.currentTimeMillis()).getTime());
    }
}