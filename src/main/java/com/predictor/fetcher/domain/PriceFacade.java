package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPair;
import com.predictor.config.kafka.KafkaProducerConfig;
import com.predictor.fetcher.adapters.KafkaRepository;
import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;

public class PriceFacade {
    private final PriceService priceService;
    public PriceFacade(KafkaProducerConfig producerConfig, PriceClient client) {
        final PriceRepository repository = new KafkaRepository(producerConfig.priceKafkaTemplate());
        priceService = new PriceService(client, repository);
    }

    public PriceFacade(PriceClient client, PriceRepository repository) {
        this.priceService = new PriceService(client, repository);
    }

    public void sendActualPriceToProcess(CurrencyPair pairKey, String topicName) {
        final Price price = priceService.fetchPrice(pairKey);
        priceService.pushPriceToQueue(price, topicName);
    }
}