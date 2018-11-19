package com.jackman.commons.exception;

/**
 * @Author shusheng
 * @Date 18/11/16 下午2:44
 */
public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(final String msg) {
        super(msg);
    }

    public BusinessException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

    public BusinessException(final Integer code, final String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(final Integer code, final String msg, final Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public BusinessException(final Integer code, final Throwable cause) {
        super(cause);
        this.code = code;
    }

}
