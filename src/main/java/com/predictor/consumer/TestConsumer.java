package com.predictor.consumer;

import com.predictor.fetcher.domain.ports.PriceDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @KafkaListener(
            topics = "BTCUSDT-BINANCE",
            containerFactory = "priceKafkaListenerContainerFactory")
    public void greetingListener(PriceDto price) {
        System.out.println("Consumer: " + price);
    }
}
