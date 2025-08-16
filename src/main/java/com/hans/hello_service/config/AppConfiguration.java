package com.hans.hello_service.config;

import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

@Configuration
public class AppConfiguration {

  @Bean
  public WebClient webClient(
      GreetServiceProperties greetServiceProperties,
      ObjectProvider<WebClientCustomizer> customizers
  ) {
    WebClient.Builder builder = WebClient.builder().baseUrl(greetServiceProperties.baseUrl());
    customizers.orderedStream().forEach(c -> c.customize(builder));
    return builder.build();
  }

  @Bean
  public WebFilter traceIdResponseHeaderFilter(Tracer tracer) {
    return (exchange, chain) -> chain.filter(exchange)
        .doFirst(() -> {
          var span = tracer.currentSpan();
          if (span != null) {
            String traceId = span.context().traceId();
            exchange.getResponse().getHeaders().add("X-Trace-Id", traceId);
          }
        });
  }
}
