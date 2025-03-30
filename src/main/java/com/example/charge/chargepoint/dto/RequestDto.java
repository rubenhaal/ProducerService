package com.example.charge.chargepoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestDto {
    @JsonProperty("station_id")
    private UUID stationId;
    @Pattern(
            regexp = "^[a-zA-Z0-9._,-]+$",
            message = "Not a valid driver ID"
    )
    @JsonProperty("driver_id")
    private String driverId;
    @Pattern(
            regexp = "^(http|https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Should be a valid URL"
    )
    @JsonProperty("callback")
    private String callback;
}
