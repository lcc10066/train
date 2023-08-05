package com.spartaMonster.train.batch.feign;

import com.spartaMonster.train.common.resp.CommonResp;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BusinessFeignFallback implements BusinessFeign {
    @Override
    public String hello() {
        return "Fallback";
    }

    @Override
    public CommonResp<Object> genDaily(Date date) {
        return null;
    }
}
