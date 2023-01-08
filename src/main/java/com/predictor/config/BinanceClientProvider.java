package com.predictor.config;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import org.springframework.beans.factory.annotation.Value;

public class BinanceClientProvider {
    @Value("${binance_api.secret}")
    private String secret;
    @Value("${binance_api.key}")
    private String apiKey;

    public BinanceApiRestClient getBinanceClient() {
        final BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey, secret);
        return factory.newRestClient();
    }
}