package com.predictor;

import com.predictor.fetcher.domain.PriceFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.predictor.common.CurrencyPairKey.BTCUSDT;

@Component
public class ActualPriceSenderToProcessScheduler {
    private final PriceFacade priceFacade;

    public ActualPriceSenderToProcessScheduler() {
        this.priceFacade = new PriceFacade();
    }

    @Scheduled(fixedRate = 10000)
    public void sendActualPriceToProcess() {
        priceFacade.sendActualPriceToProcess(BTCUSDT);
    }
}
