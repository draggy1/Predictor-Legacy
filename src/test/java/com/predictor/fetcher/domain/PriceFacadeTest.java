package com.predictor.fetcher.domain;

import com.predictor.fetcher.domain.ports.PriceDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.predictor.common.CurrencyPair.BTCUSDT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceFacadeTest {
    public static final String PRICE1 = "123.11";
    public static final String PRICE2 = "2333.41";
    public static final String PRICE3 = "4345.99";

    @Test
    public void sendActualPriceToProcess() {
        //given
        final InMemoryPriceRepository queue = new InMemoryPriceRepository();
        final PriceFacade facade = preparePriceFacade(queue);

        //when
        facade.sendActualPriceToProcess(BTCUSDT, "topicName");
        facade.sendActualPriceToProcess(BTCUSDT, "topicName");
        facade.sendActualPriceToProcess(BTCUSDT, "topicName");


        //then
        List<PriceDto> expected = getExpectedPrices();
        ArrayList<PriceDto> result = new ArrayList<>(queue.getQueue());
        assertEquals(expected, result);
    }

    @NotNull
    private static List<PriceDto> getExpectedPrices() {
        final PriceDto expectedPrice1 = Price.of(PRICE1, BTCUSDT).dto();
        final PriceDto expectedPrice2 = Price.of(PRICE2, BTCUSDT).dto();
        final PriceDto expectedPrice3 = Price.of(PRICE3, BTCUSDT).dto();

        return Arrays.asList(expectedPrice1, expectedPrice2, expectedPrice3);
    }

    @NotNull
    private static PriceFacade preparePriceFacade(InMemoryPriceRepository queue) {
        return new PriceFacade(new InMemoryCorrectPriceClient(List.of(PRICE1, PRICE2, PRICE3)), queue);
    }
}