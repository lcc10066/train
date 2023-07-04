package com.spartaMonster.train.member.controller;

import com.spartaMonster.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!!!";
    }

    @GetMapping("/count")
    public Integer count(){
        return memberService.count();
    }
}
