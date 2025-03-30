package com.example.charge.chargepoint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    private String status;
    private String message;
}
