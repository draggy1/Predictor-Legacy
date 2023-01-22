package com.predictor.fetcher.adapters;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.BinanceApiException;
import com.predictor.common.CurrencyPair;
import com.predictor.fetcher.domain.ports.PriceClient;

import java.math.BigDecimal;

public class BinancePriceClient implements PriceClient {
    private final BinanceApiRestClient client;

    public BinancePriceClient(BinanceApiRestClient client) {
        this.client = client;
    }

    @Override
    public String getActualPrice(CurrencyPair pairKey) {
        final String currencyPairKey = pairKey.getKey();
        try {
            final TickerStatistics tickerStatistics = client.get24HrPriceStatistics(currencyPairKey);
            return tickerStatistics.getLastPrice();
        } catch (BinanceApiException ex) {
            return BigDecimal.ZERO.toString();
        }
    }
}