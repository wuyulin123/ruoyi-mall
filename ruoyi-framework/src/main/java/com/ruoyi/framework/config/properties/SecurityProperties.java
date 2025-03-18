package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class SecurityProperties {

    /**
     * 排除路径
     */
    @Value("${security.excludes}")
    private String[] excludes;


}