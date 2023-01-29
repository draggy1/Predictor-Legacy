package com.predictor;


import com.predictor.fetcher.domain.ports.PriceDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaTestConsumer {

    private CountDownLatch latch = new CountDownLatch(1);

    private PriceDto priceDto;

    @KafkaListener(
            topics = "${spring.kafka.topic-1}",
            containerFactory = "priceKafkaListenerContainerFactory")
    public PriceDto receive(PriceDto priceDto) {
        return priceDto;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public PriceDto getPriceDto() {
        return priceDto;
    }
}
