package com.yuxing.trainee.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 网关白名单配置
 *
 * @author yuxing
 */
@Configuration
@ConfigurationProperties(prefix = "trainee-gateway.security")
public class WhiteListConfig {

    private List<String> whitelist;

    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }
}
