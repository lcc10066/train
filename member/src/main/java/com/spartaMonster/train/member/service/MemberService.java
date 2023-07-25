package com.spartaMonster.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.spartaMonster.train.common.exeption.BusinessException;
import com.spartaMonster.train.common.exeption.BusinessExceptionEnum;
import com.spartaMonster.train.common.util.SnowUtil;
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
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        /*
            UUID: 不连续，构建的有序索引效率太低
            数据库自增：不支持分布式数据库
            雪花算法： Twitter首发   64bit   long
            1     41      10           12
            不用  时间戳    数据中心+机器ID     12位序列号（4000个id，该毫秒内，该机器生成号用完了，再调用方法会阻塞到下一毫秒）
                （保证递增）

             每次重启可从缓存/数据库中读
             workerId: 机器id
             datacenterId: 集群id

             时钟回拨问题    时钟回拨后 重复时间段内不启动机器

         */
//        member.setId(System.currentTimeMillis());
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(req.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
}
