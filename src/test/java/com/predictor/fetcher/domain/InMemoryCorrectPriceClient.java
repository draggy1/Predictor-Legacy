package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPairKey;
import com.predictor.fetcher.domain.ports.PriceClient;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class InMemoryCorrectPriceClient implements PriceClient {
    private final LinkedList<String> returnedPrice;

    public InMemoryCorrectPriceClient(List<String> returnedPrice) {
        this.returnedPrice = new LinkedList<>(returnedPrice);
    }

    @Override
    public String getActualPrice(CurrencyPairKey currencyPairKey) {
        return returnedPrice.size() > 0 ? getAndRemove() : BigDecimal.ZERO.toString();
    }

    private String getAndRemove() {
        final String last = returnedPrice.getLast();
        returnedPrice.removeLast();
        return last;
    }
}
