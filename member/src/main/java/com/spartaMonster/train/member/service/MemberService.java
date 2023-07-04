package com.spartaMonster.train.member.service;

import com.spartaMonster.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
//    Resource 和Autowired的区别  ？？ 为啥Resource不需要为interface声明bean
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return memberMapper.count();
    };
}
