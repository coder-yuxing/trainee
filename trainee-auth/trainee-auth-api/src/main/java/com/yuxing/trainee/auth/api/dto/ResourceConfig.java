package com.yuxing.trainee.auth.api.dto;

import com.yuxing.trainee.common.core.Logic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.Set;

/**
 * 资源配置
 *
 * @author yuxing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceConfig implements Serializable {

    private static final long serialVersionUID = -1889713309960873494L;

    public ResourceConfig(String sourcePath, Set<String> requiredPermissions) {
        this.sourcePath = sourcePath;
        this.requiredPermissions = requiredPermissions;
    }

    /**
     * 资源路径
     */
    private String sourcePath;

    /**
     * 请求方法：
     * @see HttpMethod
     */
    private String method;

    /**
     * 要求的权限
     */
    private Set<String> requiredPermissions;

    /**
     * 逻辑
     */
    private Logic logic = Logic.OR;

}
