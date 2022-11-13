package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPairKey;
import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;

class PriceService {
    private final PriceClient client;
    private final PriceRepository repository;

    PriceService(PriceClient client, PriceRepository repository) {
       this.client = client;
       this.repository = repository;
    }

    Price fetchPrice(CurrencyPairKey pairKey){
        final String actualPrice = client.getActualPrice(pairKey);
        return Price.of(actualPrice);
    }

    void pushPriceToQueue(Price price){
        repository.push(price.mapToDto());
    }
}
