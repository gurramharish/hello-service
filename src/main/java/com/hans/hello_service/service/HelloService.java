package com.hans.hello_service.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@Slf4j
public class HelloService {

  private final WebClient webClient;

  HelloService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Tuple2<Map, Map>> capitalizeName(String name) {
    log.info("Give name is : {}", name);
    var response = webClient.get()
        .uri("/greet")
        .retrieve().bodyToMono(Map.class);
    var response2 = webClient.get()
        .uri("/greet?param=1")
        .retrieve().bodyToMono(Map.class);
    log.info("Call went to greet service --- :->");
    if (StringUtils.hasText(name)) {
      return Mono.zip(response, response2);
    }
    throw new RuntimeException("Name should not be null");
  }
}
