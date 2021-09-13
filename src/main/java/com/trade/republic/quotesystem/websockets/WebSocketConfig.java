package com.trade.republic.quotesystem.websockets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketConfig {

    @Value("${partner-service-base-url}")
    private String partnerServiceBaseUrl;

    @Value("${partner-service-instruments-path}")
    private String instrumentsPath;

    @Value("${partner-service-quotes-path}")
    private String quotesPath;

    @Bean
    public WebSocketConnectionManager webSocketConnectionManager() {
            WebSocketConnectionManager manager = new WebSocketConnectionManager(
                webSocketClient(),
                webSocketHandler(),
                String.format("%s/%s", partnerServiceBaseUrl, instrumentsPath)
        );
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    public WebSocketConnectionManager quotesWebSocketConnectionManager() {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                webSocketClient(),
                webSocketHandler(),
                String.format("%s/%s", partnerServiceBaseUrl, quotesPath)
        );
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public org.springframework.web.socket.WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }
}
