package com.trade.republic.quotesystem.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "partner.service")
public class PartnerServiceConfig {
    private String baseUrl;
    private String instrumentsPath;
    private String quotesPath;
}
