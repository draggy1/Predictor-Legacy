package com.predictor.fetcher.domain;

import com.predictor.fetcher.domain.ports.PriceClient;
import com.predictor.fetcher.domain.ports.PriceRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

import static com.predictor.common.CurrencyPairKey.BTCUSDT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PriceServiceTest {

    @Test
    public void correctPriceIsFetchedSuccessfully() {
        //given
        final PriceClient client = new InMemoryCorrectPriceClient(List.of("21245.5300000"));
        final PriceRepository repository = new InMemoryPriceRepository();
        final PriceService service = new PriceService(client, repository);
        final Price expectedPrice = prepareExpectedPrice("21245.53");

        //when
        final Price price = service.fetchPrice(BTCUSDT);

        //then
        assertEquals(expectedPrice, price);
    }

    @Test
   public void correctPriceRoundedHalfUpIsFetchedSuccessfully() {
        //given
        final PriceClient client = new InMemoryCorrectPriceClient(List.of("21245.53999999"));
        final PriceRepository repository = new InMemoryPriceRepository();
        final PriceService service = new PriceService(client, repository);
        final Price expectedPrice = prepareExpectedPrice("21245.54");

        //when
        final Price price = service.fetchPrice(BTCUSDT);

        //then
        assertEquals(expectedPrice, price);
    }

    @ParameterizedTest
    @MethodSource("prices")
    void inCorrectPriceFromApiShouldBeTreatedAsZero(List<String> priceValues) {
        //given
        final PriceClient client = new InMemoryCorrectPriceClient(priceValues);
        final PriceRepository repository = new InMemoryPriceRepository();
        final PriceService service = new PriceService(client, repository);
        final Price expectedPrice = Price.zero();

        //when
        final Price price = service.fetchPrice(BTCUSDT);

        //then
        assertEquals(expectedPrice, price);
    }

    private static Stream<Arguments> prices() {
        return Stream.of(
                arguments(List.of("-1")),
                arguments(List.of("sdjksd")),
                arguments(List.of("110.  234")),
                arguments(List.of("    123123.4343    ")),
                arguments(List.of("sgnjds.sdfds")),
                arguments(List.of("1.1d")),
                arguments(List.of("222,21421")),
                arguments(List.of(",345")),
                arguments(List.of("0x7b"))
        );
    }

    @NotNull
    private static Price prepareExpectedPrice(String expectedPrice) {
        return new Price(new BigDecimal(expectedPrice).setScale(2, RoundingMode.HALF_UP));
    }
}