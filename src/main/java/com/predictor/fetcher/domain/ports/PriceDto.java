package com.predictor.fetcher.domain.ports;

import com.predictor.common.CurrencyPair;

import java.math.BigDecimal;

public record PriceDto(BigDecimal price, CurrencyPair pair) {
}
