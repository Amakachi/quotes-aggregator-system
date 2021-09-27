package com.trade.republic.quotesystem.app.config;

import com.trade.republic.quotesystem.app.websockets.QuoteSystemWebSocketHandler;
import com.trade.republic.quotesystem.app.websockets.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {

    private final PartnerServiceConfig config;
    private final WebSocketService webSocketService;

    @Bean
    public WebSocketConnectionManager instrumentWebSocketConnectionManager() {
        return buildSocketConnectionManager(config.getInstrumentsPath());
    }

    @Bean
    public WebSocketConnectionManager quotesWebSocketConnectionManager() {
        return buildSocketConnectionManager(config.getQuotesPath());
    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new QuoteSystemWebSocketHandler(webSocketService);
    }

    private WebSocketConnectionManager buildSocketConnectionManager(String paths) {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                webSocketClient(),
                webSocketHandler(),
                buildUrl(paths)
        );
        manager.setAutoStartup(true);
        return manager;
    }

    private String buildUrl(String instrumentPaths) {
        return String.format("%s/%s", config.getBaseUrl(), instrumentPaths);
    }
}
