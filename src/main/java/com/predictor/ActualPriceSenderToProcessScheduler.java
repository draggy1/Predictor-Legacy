package com.predictor;

import com.predictor.config.kafka.KafkaProducerConfig;
import com.predictor.fetcher.domain.PriceFacade;
import com.predictor.fetcher.domain.ports.PriceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.predictor.common.CurrencyPair.BTCUSDT;

@Component
public class ActualPriceSenderToProcessScheduler {
    @Value(value = "${spring.kafka.topic-1}")
    private String topicName;

    private final PriceFacade facade;

    @Autowired
    public ActualPriceSenderToProcessScheduler(KafkaProducerConfig producerConfig, PriceClient client) {
        this.facade = new PriceFacade(producerConfig, client);
    }

    @Scheduled(fixedRate = 10000)
    public void sendActualPriceToProcess() {
        facade.sendActualPriceToProcess(BTCUSDT, topicName);
    }
}
