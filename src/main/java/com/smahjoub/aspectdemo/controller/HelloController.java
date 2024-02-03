package com.smahjoub.aspectdemo.controller;

import com.smahjoub.aspectdemo.annotation.PatternCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}/{value}")

    public String hello(  @PathVariable("value")  String value,

                          @PathVariable("name")  @PatternCheck(pattern = "[a-zA-Z]+")  String name) {
        return "Hello, " + name + "!";
    }
}