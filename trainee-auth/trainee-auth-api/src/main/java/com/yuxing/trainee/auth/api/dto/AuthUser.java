package com.yuxing.trainee.auth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    public AuthUser(Long id, String username, String avatar, String phone) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.phone = phone;
    }

    private Long id;

    private String username;

    private String avatar;

    private String phone;

    private Set<String> authorities;
}
