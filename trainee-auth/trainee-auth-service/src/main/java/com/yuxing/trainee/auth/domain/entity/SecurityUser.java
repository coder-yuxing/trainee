package com.yuxing.trainee.auth.domain.entity;

import cn.hutool.core.collection.CollUtil;
import com.yuxing.trainee.uac.api.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录用户信息
 *
 * @author yuxing
 */
@Data
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = 4013291840832583732L;

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    private Boolean enabled;

    /**
     * 可访问客户端
     */
    private Collection<String> clientIds;

    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(UserDTO user) {
        this.id = user.getId();
        this.username = user.getName();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.avatar = user.getAvatar();
        this.enabled = true;
        Set<UserDTO.Permission> permissions = user.getPermissions();
        if (CollUtil.isNotEmpty(permissions)) {
            clientIds = permissions.stream().map(UserDTO.Permission::getClientId).collect(Collectors.toSet());
            authorities = permissions.stream().map(UserDTO.Permission::getPermissions)
                    .map(p -> p.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
                    .flatMap(Collection::stream).collect(Collectors.toSet());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
