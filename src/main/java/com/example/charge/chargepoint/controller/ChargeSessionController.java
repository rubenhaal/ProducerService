package com.example.charge.chargepoint.controller;

import com.example.charge.chargepoint.dto.CallbackResponseDto;
import com.example.charge.chargepoint.dto.RequestDto;
import com.example.charge.chargepoint.dto.ResponseDto;
import com.example.charge.chargepoint.service.ChargeSessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/session")
@AllArgsConstructor
public class ChargeSessionController {

    private ChargeSessionService chargeSessionService;

    @PostMapping("/startsession")
    public ResponseDto initiateChargeSession(@RequestBody @Valid RequestDto requestDto){
        chargeSessionService.sendRequestToInternalService(requestDto);
        return  ResponseDto.builder()
                .status("accepted")
                .message("Request is being processed asynchronously. The result will be sent to the provided callback URL.")
                .build();
    }

    @PostMapping("/received")
    public CallbackResponseDto clientEndpointResponseExample(@RequestBody CallbackResponseDto callbackResponse){
        log.info("Everything works");
        return callbackResponse;
    }


}
