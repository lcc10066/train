package com.spartaMonster.train.member.req;

import jakarta.validation.constraints.NotBlank;

public class MemberRegisterReq {

    /**
     * 抛出异常是BindException，对应处理
     */
    @NotBlank(message = "【手机号】不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
