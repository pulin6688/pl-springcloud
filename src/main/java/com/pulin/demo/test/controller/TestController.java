package com.pulin.demo.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pulin on 2018/12/19.
 */
@Api(value = "TestController", description = "TestController")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @ApiOperation(value = "/time", notes = "获取当前时间毫秒",httpMethod ="GET" )
    @RequestMapping("/time")
    public String time(){
        return String.valueOf(System.currentTimeMillis());
    }
}
