package com.bo.springbootkafkaconsumer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping(path = "/")
    public ModelAndView home() {
        return new ModelAndView("redirect:" + "/swagger-ui/index.html");
    }


}
