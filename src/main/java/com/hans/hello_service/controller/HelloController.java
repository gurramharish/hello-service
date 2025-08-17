package com.hans.hello_service.controller;


import com.hans.hello_service.service.HelloService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

  private final HelloService helloService;

  HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping()
  public Mono<Tuple2<Map, Map>> sayHello() {
    log.info("In Hello api: {}", "Hi");
    return helloService.capitalizeName("harish kumar");
  }
}
