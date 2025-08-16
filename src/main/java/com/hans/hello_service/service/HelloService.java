package com.hans.hello_service.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HelloService {

  private final WebClient webClient;

  HelloService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Map> capitalizeName(String name) {
    log.info("Give name is : {}", name);
    var response = webClient.get()
        .uri("/greet")
        .retrieve().bodyToMono(Map.class);
    log.info("Call went to greet service --- :->");
    if (StringUtils.hasText(name)) {
      return response;
    }
    throw new RuntimeException("Name should not be null");
  }
}
