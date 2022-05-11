package com.bo.springbootkafkaconsumer.controllers;

import com.bo.springbootkafkaconsumer.services.MyProducerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/")
public class MyController {

    final MyProducerService myProducerService;

    @PostMapping("/producer/{modelname}/{nbr}")
    public String sendMessage(
            @PathVariable String modelname,
            @PathVariable Integer nbr
    ) {
        Timestamp timestampStart = new Timestamp(System.currentTimeMillis());
        myProducerService.send(modelname, nbr);
        Timestamp timestampEnd = new Timestamp(System.currentTimeMillis());
        float totalTime = timestampEnd.getTime() - timestampStart.getTime();
        return nbr + " message(s) sent successfully in " + totalTime/1000 + " seconds";
    }
}
