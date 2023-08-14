package com.spartaMonster.train.member.controller.admin;

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
@RequestMapping("/admin/ticket")
public class TicketAdminController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return new CommonResp<>(list);
    }

}
