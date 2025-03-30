package com.example.charge.chargepoint.service;

import com.example.charge.chargepoint.dto.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMqProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MessageDto message ){
        rabbitTemplate.convertAndSend("", "charge.queue", message);
    }
}
