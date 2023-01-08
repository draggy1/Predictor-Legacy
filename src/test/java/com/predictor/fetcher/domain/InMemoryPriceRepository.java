package com.predictor.fetcher.domain;

import com.predictor.fetcher.domain.ports.PriceDto;
import com.predictor.fetcher.domain.ports.PriceRepository;

import java.util.ArrayDeque;

public class InMemoryPriceRepository implements PriceRepository {
    private final ArrayDeque<PriceDto> queue = new ArrayDeque<>();

    @Override
    public void push(PriceDto price, String topicName) {
        queue.push(price);
    }

    public ArrayDeque<PriceDto> getQueue() {
        return queue;
    }
}
