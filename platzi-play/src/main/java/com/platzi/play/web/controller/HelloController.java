package com.platzi.play.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

     private final String port;

     public HelloController(@Value("${server.port}") String port) {
         this.port = port;
     }

     @GetMapping("/")
    public String hello() {
         return "Hello World " + this.port;
     }

}
