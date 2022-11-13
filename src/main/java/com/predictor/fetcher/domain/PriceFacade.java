package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPairKey;
import com.predictor.fetcher.adapters.BinancePriceClient;
import com.predictor.fetcher.adapters.InMemoryPriceRepositoryTemporary;
import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;

public class PriceFacade {
    final PriceService priceService;

    public PriceFacade() {
        final PriceClient client = new BinancePriceClient();
        final PriceRepository repository = new InMemoryPriceRepositoryTemporary();
        priceService = new PriceService(client, repository);
    }

    public PriceFacade(PriceClient client, PriceRepository repository) {
        this.priceService = new PriceService(client, repository);
    }

    public void sendActualPriceToProcess(CurrencyPairKey pairKey){
        final Price price = priceService.fetchPrice(pairKey);
        priceService.pushPriceToQueue(price);
    }
}