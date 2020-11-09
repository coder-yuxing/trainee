package com.yuxing.trainee.auth.api.manager;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.auth.api.dto.AuthUser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录用户操作
 *
 * @author yuxing
 */
@Component
public class LoginUserManager {

    /**
     * 获取当前用户
     *
     * @return 返回当前用户对象
     */
    public AuthUser getCurrentUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new RuntimeException();
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userMsg = request.getHeader(AuthConstant.USER_MSG_HEADER);
        if (StrUtil.isBlank(userMsg)) {
            throw new RuntimeException();
        }
        return JSONUtil.parseObj(userMsg).toBean(AuthUser.class);
    }

    /**
     * 获取当前登录用户ID
     *
     * @return 用户ID
     */
    public Long getUserId() {
        AuthUser authUser = this.getCurrentUser();
        return authUser.getId();
    }
}
