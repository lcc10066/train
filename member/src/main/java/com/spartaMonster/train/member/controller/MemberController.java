package com.spartaMonster.train.member.controller;

import com.spartaMonster.train.common.resp.CommonResp;
import com.spartaMonster.train.member.req.MemberRegisterReq;
import com.spartaMonster.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req){
        long register = memberService.register(req);
        CommonResp<Long> resp = new CommonResp<>();
        resp.setContent(register);
        return resp;
    }
}
