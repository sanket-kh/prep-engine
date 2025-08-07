package com.praxium.prepengine.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DefaultController {
    @GetMapping("/")
    public String home() {
       return  "PrepEngine";
    }
}