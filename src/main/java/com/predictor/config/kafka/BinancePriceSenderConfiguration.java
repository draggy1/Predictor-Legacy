package com.predictor.config.kafka;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.predictor.fetcher.adapters.BinancePriceClient;
import com.predictor.fetcher.domain.ports.PriceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinancePriceSenderConfiguration {
    @Value("${binance_api.secret}")
    private String binanceSecret;
    @Value("${binance_api.key}")
    private String binanceApiKey;
    final BinanceApiRestClient binanceApiRestClient = BinanceApiClientFactory
            .newInstance(binanceApiKey, binanceSecret)
            .newRestClient();

    @Bean
    public PriceClient binancePriceClient() {
        return new BinancePriceClient(binanceApiRestClient);
    }
}
