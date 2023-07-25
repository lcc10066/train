package com.spartaMonster.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.spartaMonster.train.member.domain.Member;
import com.spartaMonster.train.member.domain.MemberExample;
import com.spartaMonster.train.member.mapper.MemberMapper;
import com.spartaMonster.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
//    Resource 和Autowired的区别  ？？ 为啥Resource不需要为interface声明bean
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    };

    public long register(MemberRegisterReq req){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(req.getMobile());
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(members)){
            throw new RuntimeException("手机号已注册");
        }
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(req.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
}
