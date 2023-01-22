package com.predictor.fetcher.domain;

import com.predictor.common.CurrencyPair;
import com.predictor.fetcher.domain.ports.PriceDto;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.predictor.common.CurrencyPair.NONE;
import static java.math.BigDecimal.ZERO;

record Price(BigDecimal priceValue, CurrencyPair pair) {

    static Price of(String priceValue, CurrencyPair pair) {
        final Pair<BigDecimal, CurrencyPair> priceWithCurrency = castPriceToBigDecimal(priceValue, pair);
        return new Price(priceWithCurrency.getLeft(), priceWithCurrency.getRight());
    }

    static Price zero() {
        return new Price(ZERO, NONE);
    }

    PriceDto dto() {
        return new PriceDto(priceValue, pair);
    }

    private static Pair<BigDecimal, CurrencyPair> castPriceToBigDecimal(String priceValue, CurrencyPair pair) {
        return Optional.ofNullable(priceValue)
                .filter(NumberUtils::isParsable)
                .map(Price::createBigDecimal)
                .filter(Price::isPositiveNumber)
                .map(price -> Pair.of(price, pair))
                .orElse(Pair.of(ZERO, NONE));
    }

    private static boolean isPositiveNumber(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    @NotNull
    private static BigDecimal createBigDecimal(String priceValue) {
        return new BigDecimal(priceValue).setScale(2, RoundingMode.HALF_UP);
    }
}
