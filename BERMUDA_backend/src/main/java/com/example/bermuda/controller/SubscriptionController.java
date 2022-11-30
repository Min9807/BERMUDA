package com.example.bermuda.controller;

import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.SubscriptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "subscribe", description = "구독 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscriptionController {

    final private SubscriptionService subscriptionService;
    final private ResponseService responseService;

    @ApiOperation(value = "구독 결제창", notes = "구독을 할지 안할지 확인받는다.")
    @PutMapping("/subscribe")
    public CommonResult checkSubscription(){

        subscriptionService.checkSubscription();

        return responseService.getSuccessResult();
    }


}
