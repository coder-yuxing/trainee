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

    public Result(BaseEnum base) {
        BaseResult result = base.toResult();
        this.code = result.getCode();
        this.message = result.getMessage();
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

    public static <T> Result<T> failed(BaseEnum base) {
        return restResult(base);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResultCode.BAD_REQUEST.getCode(), msg);
    }

    private static <T> Result<T> restResult(T data, BaseEnum base) {
        BaseResult result = base.toResult();
        return restResult(data, result.getCode(), result.getMessage());
    }

    private static <T> Result<T> restResult(BaseEnum base) {
        BaseResult result = base.toResult();
        return restResult(null, result.getCode(), result.getMessage());
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
