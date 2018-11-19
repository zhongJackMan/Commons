package com.jackman.commons.enums;

import com.jackman.commons.exception.MissingEnumCodeException;

/**
 * 基础状态枚举
 * 只提供 初始化, 有效, 失效三种状态的枚举值;
 * 若有其他字段, 请自行构建枚举;
 * @Author shusheng
 * @Date 18/11/16 下午5:20
 */
public enum  BaseStatusEnum {
    INIT(0, "初始化"), EFFECTIVE(1, "有效"), INVALID(-1, "失效");

    BaseStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public static String queryMsgByCode(final Integer code) {
        for(BaseStatusEnum baseStatusEnum : values()) {
            if(baseStatusEnum.code.equals(code)) {
                return baseStatusEnum.msg;
            }
        }
        throw new MissingEnumCodeException("根据传入的code获取信息为空!");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
