package com.spartaMonster.train.member.controller;

import com.spartaMonster.train.common.resp.CommonResp;
import com.spartaMonster.train.member.req.MemberLoginReq;
import com.spartaMonster.train.member.req.MemberRegisterReq;
import com.spartaMonster.train.member.req.MemberSendCodeReq;
import com.spartaMonster.train.member.resp.MemberLoginResp;
import com.spartaMonster.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        return new CommonResp<>(memberService.count());
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/sendCode")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
