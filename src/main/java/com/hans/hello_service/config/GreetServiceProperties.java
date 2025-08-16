package com.hans.hello_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.greet-service")
public record GreetServiceProperties(String baseUrl) {}
