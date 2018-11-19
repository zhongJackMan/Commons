package com.jackman.commons.exception;

/**
 * 枚举查询为空异常
 * @Author shusheng
 * @Date 18/11/16 下午5:25
 */
public class MissingEnumCodeException extends RuntimeException {

    public MissingEnumCodeException() {
        super();
    }

    public MissingEnumCodeException(final String msg) {
        super(msg);
    }
}
