package com.spartaMonster.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.spartaMonster.train.member.domain.Member;
import com.spartaMonster.train.member.domain.MemberExample;
import com.spartaMonster.train.member.mapper.MemberMapper;
import com.spartaMonster.train.common.exception.BusinessException;
import com.spartaMonster.train.common.exception.BusinessExceptionEnum;
import com.spartaMonster.train.common.util.JwtUtil;
import com.spartaMonster.train.common.util.SnowUtil;
import com.spartaMonster.train.member.req.MemberLoginReq;
import com.spartaMonster.train.member.req.MemberRegisterReq;
import com.spartaMonster.train.member.req.MemberSendCodeReq;
import com.spartaMonster.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MemberService {
//    Resource 和Autowired的区别  ？？ 为啥Resource不需要为interface声明bean
    @Resource
    private MemberMapper memberMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);


    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    };


    public Member selectByMobile(String mobile){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(members)){
            return null;
        }
        return members.get(0);
    }

    public long register(MemberRegisterReq req){
        Member memberDB = selectByMobile(req.getMobile());
        if(ObjectUtil.isNotNull(memberDB)){
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


    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

//        String code = RandomUtil.randomString(4);
        /*  保存短信记录   手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
         *  primary key(手机号)
         *  首先查询是否有该记录
         *  有则更新，无则新建
         *
         *  调用短信通道发送短信
         */
        String code = "8888";

        LOG.info("保存短信记录表");

        LOG.info("对接短信通道");

    }


    /*
        token组成与验证原理 ：   荷载  +   header   +  签名
        https://www.cnblogs.com/lufeiludaima/p/pz20190203.html
     */


    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();

        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        if (!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }


        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }
}
