package com.predictor.common;

public enum CurrencyPairKey {
    BTCUSDT("BTCUSDT");
    private final String key;

    public String getKey() {
        return key;
    }

    CurrencyPairKey(String key) {
        this.key = key;
    }
}
