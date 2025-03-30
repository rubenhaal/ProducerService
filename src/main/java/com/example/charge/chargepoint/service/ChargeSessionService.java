package com.example.charge.chargepoint.service;

import com.example.charge.chargepoint.dto.MessageDto;
import com.example.charge.chargepoint.dto.RequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChargeSessionService {
    private RabbitMqProducer producer;
    private ObjectMapper objectMapper;

    public void sendRequestToInternalService(RequestDto requestDto){
        producer.sendMessage(objectMapper.convertValue(requestDto, MessageDto.class));
    }
}
