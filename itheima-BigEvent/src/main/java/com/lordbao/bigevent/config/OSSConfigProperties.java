package com.lordbao.bigevent.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Lord_Bao
 * @Date 2025/2/13 16:51
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "my-oss")
@Component
@Data
public class OSSConfigProperties {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;//外链域名
}
