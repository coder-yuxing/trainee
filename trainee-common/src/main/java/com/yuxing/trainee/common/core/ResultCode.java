package com.yuxing.trainee.common.core;

/**
 * 接口响应状态码
 *
 * @author yuxing
 */
public enum ResultCode {

    /* 成功 */
    SUCCESS(1, "success"),

    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    SESSION_TIMEOUT(440, "Session Timeout"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ;



    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
