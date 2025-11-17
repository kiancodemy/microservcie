package org.example.clients;

import feign.RequestInterceptor;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTracingConfig {

    private final Tracer tracer;

    public FeignTracingConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            var currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                var context = currentSpan.context();

                // B3 headers
                requestTemplate.header("X-B3-TraceId", context.traceId());
                requestTemplate.header("X-B3-SpanId", context.spanId());

                if (context.parentId() != null) {
                    requestTemplate.header("X-B3-ParentSpanId", context.parentId());
                }

                requestTemplate.header("X-B3-Sampled", "1");
            }
        };
    }
}