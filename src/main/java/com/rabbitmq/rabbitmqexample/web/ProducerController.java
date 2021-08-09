package com.rabbitmq.rabbitmqexample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.rabbitmqexample.domain.User;
import com.rabbitmq.rabbitmqexample.service.RabbitMqSender;

/**
 * 
 * @author ali
 *
 */
@RestController
@RequestMapping(value = "/api/v1/")
public class ProducerController {
    
	private RabbitMqSender rabbitMqSender;
    
	@Autowired
    public ProducerController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }
    
	@Value("${app.message}")
    private String message;
    
	@GetMapping(value = "user")
    public String getUserDetails() {
        return message;
    }
	
	@PostMapping(value = "user")
    public String publishUserDetails(@RequestBody User user) {
        rabbitMqSender.send(user);
        return message;
    }
}
