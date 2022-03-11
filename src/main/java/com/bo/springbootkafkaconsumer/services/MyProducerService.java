package com.bo.springbootkafkaconsumer.services;

import com.bo.springbootkafkaconsumer.models.MyModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyProducerService {

    @Autowired
    private KafkaTemplate<String, MyModel> kafkaTemplate;

    String kafkaTopic = "my-topic";

    public void send(MyModel myModel) {
        log.info("Sending my model Json Serializer : {}", myModel);
        kafkaTemplate.send(kafkaTopic, myModel);
    }

}
