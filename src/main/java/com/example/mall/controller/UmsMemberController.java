package com.example.mall.controller;

import com.example.mall.common.api.CommonResult;
import com.example.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "UmsMemberController",description = "会员登陆注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    UmsMemberService service;
    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone){
        return service.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyAuthCode")
    public CommonResult updatePassword(@RequestParam String telephone,@RequestParam String authCode){
        return service.verifyAuthCode(telephone,authCode);
    }
}
