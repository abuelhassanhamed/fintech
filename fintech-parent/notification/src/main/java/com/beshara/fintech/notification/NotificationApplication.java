package com.beshara.fintech.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@SpringBootApplication
@EnableWebSocket

public class NotificationApplication implements WebSocketConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(notificationHandler(), "/notifications").addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOriginPatterns("*").withSockJS();
	}

	@Bean
	public WebSocketHandler notificationHandler() {
		return new WebNotificationHandler();
	}


}
