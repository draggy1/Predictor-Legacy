package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPair;
import com.predictor.config.kafka.KafkaProducerConfig;
import com.predictor.fetcher.adapters.BinancePriceClient;
import com.predictor.fetcher.adapters.KafkaRepository;
import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceFacade {
    private final PriceService priceService;

    @Autowired
    public PriceFacade(KafkaProducerConfig producerConfig) {
        final PriceClient client = new BinancePriceClient();
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