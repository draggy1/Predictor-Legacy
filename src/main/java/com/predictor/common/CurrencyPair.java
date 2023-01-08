package com.predictor.common;

public enum CurrencyPair {
    BTCUSDT("BTCUSDT"),
    NONE("None");
    private final String key;

    public String getKey() {
        return key;
    }

    CurrencyPair(String key) {
        this.key = key;
    }
}
