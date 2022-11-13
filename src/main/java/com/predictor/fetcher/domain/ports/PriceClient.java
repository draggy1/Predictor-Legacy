package com.predictor.fetcher.domain.ports;

import com.predictor.common.CurrencyPairKey;

public interface PriceClient {
    String getActualPrice(CurrencyPairKey currencyPairKey);
}
