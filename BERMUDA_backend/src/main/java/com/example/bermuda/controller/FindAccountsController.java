package com.example.bermuda.controller;

import com.example.bermuda.dto.UserDto;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.FindAccountsService;
import com.example.bermuda.service.ResponseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Tag(name = "find accounts", description = "계정 찾기 api")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class FindAccountsController {

    private final FindAccountsService findAccountsService;
    private final ResponseService responseService;

    @ApiOperation(value = "아이디 찾기", notes = "아이디를 찾는다.")
    @GetMapping(value = "/find-id")
    public SingleResult findId(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        HashMap<String, String> userId = new HashMap<>();
        userId.put("userId", findAccountsService.findIdDto(name, email));
        return responseService.getSingleResult(userId);
    }

    /* 비밀번호 찾기 */
    // 아이디, 이메일이 일치하는 user가 있는지 체크
    @ApiOperation(value = "비밀번호 찾기", notes = "비밀번호를 찾는다.")
    @PostMapping("/find-pw")
    public CommonResult findPw(@RequestBody UserDto.FindPwDto findPwDto) {

        findAccountsService.findPwDto(findPwDto.getUserId(), findPwDto.getEmail(), findPwDto.getUserName());

        return responseService.getSuccessResult();
    }
}
