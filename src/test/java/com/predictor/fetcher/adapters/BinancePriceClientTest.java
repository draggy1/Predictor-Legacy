package com.predictor.fetcher.adapters;

import com.predictor.common.CurrencyPair;
import org.junit.jupiter.api.Test;

import static com.predictor.common.CurrencyPair.NONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinancePriceClientTest {
    final BinanceApiRestTestClient restTestClient = new BinanceApiRestTestClient();
    final BinancePriceClient tested = new BinancePriceClient(restTestClient);
    @Test
    void shouldGetActualPrice() {
        String actualPrice = tested.getActualPrice(CurrencyPair.BTCUSDT);
        assertEquals("12345.99", actualPrice);
    }

    @Test
    void shouldClientThrowsException() {
        String actualPrice = tested.getActualPrice(NONE);
        assertEquals(ZERO.toString(), actualPrice);
    }
}