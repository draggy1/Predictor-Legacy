package com.predictor;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.predictor.config.kafka.KafkaProducerConfig;
import com.predictor.fetcher.adapters.BinancePriceClient;
import com.predictor.fetcher.domain.PriceFacade;
import com.predictor.fetcher.domain.ports.PriceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.predictor.common.CurrencyPair.BTCUSDT;

@Component
public class ActualPriceSenderToProcessScheduler {
    private final PriceFacade facade;
    @Value(value = "${spring.kafka.topic-1}")
    private String topicName;
    @Value("${binance_api.secret}")
    private String binanceSecret;
    @Value("${binance_api.key}")
    private String binanceApiKey;

    @Autowired
    public ActualPriceSenderToProcessScheduler(KafkaProducerConfig producerConfig) {
        final BinanceApiRestClient binanceApiRestClient = BinanceApiClientFactory
                .newInstance(binanceApiKey, binanceSecret)
                .newRestClient();

        final PriceClient client = new BinancePriceClient(binanceApiRestClient);
        this.facade = new PriceFacade(producerConfig, client);
    }

    @Scheduled(fixedRate = 10000)
    public void sendActualPriceToProcess() {
        facade.sendActualPriceToProcess(BTCUSDT, topicName);
    }
}
