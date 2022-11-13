package com.predictor.fetcher.domain;

import com.predictor.fetcher.domain.ports.PriceDto;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

record Price(BigDecimal priceValue) {

    static Price of(String priceValue) {
        final BigDecimal price = castPriceToBigDecimal(priceValue);
        return new Price(price);
    }

    static Price zero() {
        return new Price(ZERO);
    }

    PriceDto mapToDto() {
        return new PriceDto(priceValue);
    }

    private static BigDecimal castPriceToBigDecimal(String priceValue) {
        return Optional.ofNullable(priceValue)
                .filter(NumberUtils::isParsable)
                .map(Price::createBigDecimal)
                .filter(Price::isPositiveNumber)
                .orElse(ZERO);
    }

    private static boolean isPositiveNumber(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    @NotNull
    private static BigDecimal createBigDecimal(String priceValue) {
        return new BigDecimal(priceValue).setScale(2, RoundingMode.HALF_UP);
    }
}
