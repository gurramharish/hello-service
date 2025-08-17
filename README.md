# hello-service

1. It is simple spring boot reactive api project with tracing.
2. It has logback-spring.xml with spring profile for log configuration.
3. hello-service make calls to greet-service apis using webclient in non-blocking.
4. Webclient in configured in way to pass trace-id from hello-service to the calling service in headers, so trace continue in other services.
5. 