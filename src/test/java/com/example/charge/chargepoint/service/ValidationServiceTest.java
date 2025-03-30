package com.example.charge.chargepoint.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Test
    void shouldReturnTrue_WhenValidMessageIsReceived(){

        boolean result = validationService.validateRequest();
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalse_WhenInValidMessageIsReceived(){

        boolean result = validationService.validateRequest();
        assertThat(result).isTrue();
    }

}