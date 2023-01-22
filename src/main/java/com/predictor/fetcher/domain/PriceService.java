package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPair;
import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;

class PriceService {
    private final PriceClient client;
    private final PriceRepository repository;

    PriceService(PriceClient client, PriceRepository repository) {
       this.client = client;
       this.repository = repository;
    }

    Price fetchPrice(CurrencyPair pairKey){
        final String actualPrice = client.getActualPrice(pairKey);
        return Price.of(actualPrice, pairKey);
    }

    void pushPriceToQueue(Price price, String topicName){
        repository.push(price.dto(), topicName);
    }
}
