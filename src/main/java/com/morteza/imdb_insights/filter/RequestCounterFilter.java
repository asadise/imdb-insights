package com.morteza.imdb_insights.filter;

import com.morteza.imdb_insights.service.CounterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestCounterFilter extends OncePerRequestFilter {

    private final CounterService counterService;
    private static final Set<String> EXCLUDED = Set.of("/metrics", "/favicon.ico", "/error");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        if (EXCLUDED.stream().noneMatch(request.getRequestURI()::startsWith))
            counterService.increment();

        filterChain.doFilter(request, response);

        long time = System.currentTimeMillis() - start;
        log.info("Request: {} {} | time:{}ms", request.getMethod(), request.getRequestURI(), time);
    }
}