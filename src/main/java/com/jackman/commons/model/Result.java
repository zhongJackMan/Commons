package com.jackman.commons.model;

import java.io.Serializable;

/**
 * facade调用时, 封装的结果;
 * 约定: 得不到期望的结果是, 被调用方会抛出异常!
 * @Author shusheng
 * @Date 18/11/19 下午2:27
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7230782638845396568L;
    private String code;
    private String msg;
    private T data;
    private Boolean isSuccess = false;

    /**
     * 构建一个失败的Result;
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildFailed(final String code, final String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }

    /**
     * 构建一个成功的Result;
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildSuccess(final T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode("0");
        result.setMsg("SUCCESS");
        return result;
    }

    /**
     * 直接获取结果;
     * 如果是失败, 抛出异常!
     * @return
     */
    public T recreate() {
        if(!this.isSuccess) {
            throw new RuntimeException(this.msg);
        }
        return this.data;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
