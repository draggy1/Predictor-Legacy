package com.predictor;

import com.predictor.fetcher.domain.PriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.predictor.common.CurrencyPair.BTCUSDT;

@Component
public class ActualPriceSenderToProcessScheduler {
    private final PriceFacade priceFacade;

    @Value(value = "${spring.kafka.topic-1}")
    private String topicName;

    @Autowired
    public ActualPriceSenderToProcessScheduler(PriceFacade priceFacade) {
        this.priceFacade = priceFacade;
    }

    @Scheduled(fixedRate = 10000)
    public void sendActualPriceToProcess() {
        priceFacade.sendActualPriceToProcess(BTCUSDT, topicName);
    }
}
