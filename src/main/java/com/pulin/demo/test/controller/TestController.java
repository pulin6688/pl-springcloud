package com.pulin.demo.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pulin on 2018/12/19.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("/millis")
    public String test(){
        return String.valueOf(System.currentTimeMillis());
    }
}
