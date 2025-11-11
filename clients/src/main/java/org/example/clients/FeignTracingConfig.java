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
                String traceId = context.traceId();
                String spanId = context.spanId();
                String parentSpanId = context.parentId(); // NEW: parent span id

                // Inject B3 headers
                requestTemplate.header("X-B3-TraceId", traceId);
                requestTemplate.header("X-B3-SpanId", spanId);

                // Only set parent if it exists
                if (parentSpanId != null) {
                    requestTemplate.header("X-B3-ParentSpanId", parentSpanId);
                }

                // Optional: set sampling flag if needed
                requestTemplate.header("X-B3-Sampled", "1");
            }
        };
    }
}
