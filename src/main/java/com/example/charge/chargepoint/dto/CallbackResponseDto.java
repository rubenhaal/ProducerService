package com.example.charge.chargepoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CallbackResponseDto {
    private UUID stationId;
    private String driverToken;
    private String status;
}
