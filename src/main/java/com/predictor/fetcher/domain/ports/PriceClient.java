package com.predictor.fetcher.domain.ports;

import com.predictor.common.CurrencyPair;

public interface PriceClient {
    String getActualPrice(CurrencyPair currencyPair);
}