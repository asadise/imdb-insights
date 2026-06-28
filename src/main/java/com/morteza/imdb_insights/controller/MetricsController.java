package com.morteza.imdb_insights.controller;

import com.morteza.imdb_insights.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final CounterService counterService;

    @GetMapping("/request-count")
    public long getRequests() {
        return counterService.getCount();
    }
}
