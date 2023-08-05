package com.spartaMonster.train.business.req;

import com.spartaMonster.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTrainQueryReq extends PageReq {
    /**
     *
     * GET请求使用
     * @DateTimeFormat(pattern = "yyyy-MM-dd") 该注解将前端的请求参数映射为Date类型，指定格式，否则前端传来的字符串后端接口无法识别
     *
     *
     * @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") 只适用于POST请求
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String code;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DailyTrainQueryReq{" +
                "date=" + date +
                ", code='" + code + '\'' +
                "} " + super.toString();
    }
}
