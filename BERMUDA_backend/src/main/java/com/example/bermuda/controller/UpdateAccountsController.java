package com.example.bermuda.controller;

import com.example.bermuda.dto.UserDto;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.UpdateAccountsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Tag(name = "update accounts", description = "계정 변경 api")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class UpdateAccountsController {

    private final UpdateAccountsService updateAccountsService;
    private final ResponseService responseService;

    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경한다.")
    @PutMapping(value = "/password")
    public CommonResult updatePw(@RequestBody UserDto.ChangePwDto changePwDto) {
        updateAccountsService.UpdatePwDto(changePwDto.getCurPw(), changePwDto.getNewPw());

        return responseService.getSuccessResult();
    }

}
