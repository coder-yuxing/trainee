package com.yuxing.trainee.common.core;

import java.io.Serializable;

/**
 * 统一接口响应格式
 *
 * @author yuxing
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5818131758898987235L;

    private Integer code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return restResult(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, ResultCode.SUCCESS);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(ResultCode.BAD_REQUEST);
    }

    public static <T> Result<T> failed(ResultCode resultCode) {
        return restResult(resultCode);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResultCode.BAD_REQUEST.getCode(), msg);
    }

    private static <T> Result<T> restResult(T data, ResultCode resultCode) {
        return restResult(data, resultCode.getCode(), resultCode.getMessage());
    }

    private static <T> Result<T> restResult(ResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMessage());
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
