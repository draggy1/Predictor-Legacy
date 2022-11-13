package com.predictor.fetcher.adapters;

import com.predictor.fetcher.domain.ports.PriceDto;
import com.predictor.fetcher.domain.ports.PriceRepository;

import java.util.ArrayDeque;

public class InMemoryPriceRepositoryTemporary implements PriceRepository {
    private final ArrayDeque<PriceDto> queue = new ArrayDeque<>();

    @Override
    public void push(PriceDto price) {
        queue.push(price);
    }

    public PriceDto getLast() {
        return queue.peek();
    }
}
