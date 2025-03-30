package com.example.charge.chargepoint.integration;

import com.example.charge.chargepoint.dto.MessageDto;
import com.example.charge.chargepoint.dto.RequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InitiateSessionIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private RabbitTemplate rabbitTemplate;


    @Test
    void testSendEndpointSendsMessageToRabbit() {
        //given
        RequestDto requestDto = new RequestDto();
        requestDto.setCallback("http://driver-test/30d");
        requestDto.setDriverId("30d");
        UUID stationId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        requestDto.setStationId(stationId);
        // when
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/session/startsession", requestDto, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ArgumentCaptor<MessageDto> captor = ArgumentCaptor.forClass(MessageDto.class);
        verify(rabbitTemplate).convertAndSend(eq(""),eq("charge.queue"), captor.capture());
        MessageDto messageSent = captor.getValue();
        assertThat(messageSent.getCallback()).isEqualTo("http://driver-test/30d");
        assertThat(messageSent.getDriverId()).isEqualTo("30d");
        assertThat(messageSent.getStationId()).isEqualTo(stationId);

    }
}
