package com.yuxing.trainee.uac.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientPermission {

    private String clientId;

    private Set<String> permissions;
}
