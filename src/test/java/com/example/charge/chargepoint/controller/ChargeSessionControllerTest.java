package com.example.charge.chargepoint.controller;

import com.example.charge.chargepoint.service.ChargeSessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChargeSessionController.class)
class ChargeSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ChargeSessionService chargeSessionService;

    @Test
    void testBasicRequest() throws Exception {
        String json = """
            {
              "station_id": "550e8400-e29b-41d4-a716-446655440000",
              "driver_id": "30d",
              "callback" : "http://driver-test/30"
            }
            """;

        mockMvc.perform(post("/api/session/startsession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("accepted"))
                .andExpect(jsonPath("$.message").value("Request is being processed asynchronously. The result will be sent to the provided callback URL."));

    }


    @Test
    void shouldReturn400_whenInvalidRequestIsSent() throws Exception {
        String json = """
            {
              "station_id": "session",
              "driver_id": "&%",
              "callback" : "http://driver-test/30"
            }
            """;

        mockMvc.perform(post("/api/session/startsession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(400));
    }
}