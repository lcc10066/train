package com.spartaMonster.train.member.controller;

import com.spartaMonster.train.common.context.LoginMemberContext;
import com.spartaMonster.train.common.resp.CommonResp;
import com.spartaMonster.train.common.resp.PageResp;
import com.spartaMonster.train.member.req.TicketQueryReq;
import com.spartaMonster.train.member.resp.TicketQueryResp;
import com.spartaMonster.train.member.service.TicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> query(@Valid TicketQueryReq req) {
        CommonResp<PageResp<TicketQueryResp>> commonResp = new CommonResp<>();
        req.setMemberId(LoginMemberContext.getId());
        PageResp<TicketQueryResp> pageResp = ticketService.queryList(req);
        commonResp.setContent(pageResp);
        return commonResp;
    }
}
