package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPair;
import com.predictor.fetcher.domain.ports.PriceClient;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BinanceTestPriceClient implements PriceClient {
    private final LinkedList<String> returnedPrice;

    public BinanceTestPriceClient(List<String> returnedPrice) {
        this.returnedPrice = new LinkedList<>(returnedPrice);
    }

    @Override
    public String getActualPrice(CurrencyPair currencyPair) {
        return returnedPrice.size() > 0 ? getAndRemove() : BigDecimal.ZERO.toString();
    }

    public void addPrices(List<String> prices) {
        returnedPrice.addAll(prices);
    }

    private String getAndRemove() {
        final String last = returnedPrice.getLast();
        returnedPrice.removeLast();
        return last;
    }
}
