package com.yuxing.trainee.auth.infrastructure.constant;

import com.yuxing.trainee.common.core.BaseEnum;
import com.yuxing.trainee.common.core.BaseResult;
import lombok.Getter;

/**
 * 接口响应状态码
 *
 * @author yuxing
 */
@Getter
public enum ResultCode implements BaseEnum {

    /**
     * 认证服务异常
     */
    USERNAME_PASSWORD_ERROR(400000, "用户名或密码错误!"),
    CREDENTIALS_EXPIRED(400001, "该账户的登录凭证已过期，请重新登录!"),
    ACCOUNT_DISABLED(400002, "该账户已被禁用，请联系管理员!"),
    ACCOUNT_LOCKED(400003, "该账号已被锁定，请联系管理员!"),
    ACCOUNT_EXPIRED(400004, "该账号已过期，请联系管理员!"),
    PERMISSION_DENIED(400005, "有访问权限，请联系管理员!")
    ;


    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public BaseResult toResult() {
        return null;
    }

    private final int code;

    private final String message;


}
