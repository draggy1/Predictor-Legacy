package com.predictor.fetcher.domain.ports;

public interface PriceRepository {
    void push(PriceDto price);
}
