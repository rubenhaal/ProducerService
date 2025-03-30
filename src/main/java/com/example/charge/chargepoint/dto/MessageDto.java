package com.example.charge.chargepoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MessageDto {
    @JsonProperty("station_id")
    private UUID stationId;
    @JsonProperty("driver_id")
    private String driverId;
    @JsonProperty("callback")
    private String callback;

}
