package com.bo.springbootkafkaconsumer.services;

import com.bo.springbootkafkaconsumer.models.MyModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyProducerService {

    @Autowired
    private KafkaTemplate<String, MyModel> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    public void send(MyModel myModel, Integer nbr) {
        log.info("Sending my model Json Serializer : {}", myModel);

        for (int i = 0; i < nbr; i++) {
            myModel.setModelId(i);
            kafkaTemplate.send(kafkaTopic, myModel);
        }
    }

}
