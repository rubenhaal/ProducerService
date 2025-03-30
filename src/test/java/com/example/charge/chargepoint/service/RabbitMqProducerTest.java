package com.example.charge.chargepoint.service;

import com.example.charge.chargepoint.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMqProducerTest {


    public static final String TEST_URL = "http://test.com";
    public static final String DRIVER_ID = "30d";
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMqProducer producer;

    @Test
    void shouldSendMessageToQueue() {
        UUID stationId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        MessageDto message = new MessageDto(stationId, DRIVER_ID, TEST_URL);

        producer.sendMessage(message);

        ArgumentCaptor<MessageDto> captor = ArgumentCaptor.forClass(MessageDto.class);
        verify(rabbitTemplate).convertAndSend(eq(""),eq("charge.queue"), captor.capture());

        MessageDto sent = captor.getValue();
        assertThat(sent.getStationId()).isEqualTo(stationId);
        assertThat(sent.getCallback()).isEqualTo(TEST_URL);
        assertThat(sent.getDriverId()).isEqualTo(DRIVER_ID);
    }
}