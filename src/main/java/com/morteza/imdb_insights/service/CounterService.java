package com.morteza.imdb_insights.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class CounterService {

    private final AtomicLong counter = new AtomicLong(0); //AtomicLong is thread-safe

    public void increment() {
        counter.incrementAndGet();
    }

    public long getCount() {
        return counter.get();
    }
}