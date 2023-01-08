package com.predictor.fetcher.domain.ports;

import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository {
    void push(PriceDto price, String topicName);
}
