package com.bo.springbootkafkaconsumer.controllers;

import com.bo.springbootkafkaconsumer.models.MyModel;
import com.bo.springbootkafkaconsumer.services.MyProducerService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/")
public class MyController {

    final MyProducerService myProducerService;

    @PostMapping("/producer/{nbr}")
    public String sendMessage(
        @RequestBody MyModel myModel,
        @PathVariable Integer nbr
    ) {
        myProducerService.send(myModel, nbr);
        return "Message sent successfully";
    }
}
