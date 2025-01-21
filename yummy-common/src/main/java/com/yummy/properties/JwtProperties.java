package com.yummy.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "yummy.jwt")
@Data
public class JwtProperties {
    /**
     * jwt configuration for admin
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * jwt configuration for users
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
