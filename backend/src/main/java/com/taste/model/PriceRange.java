package com.taste.model;

public enum PriceRange {
    BUDGET("$"),        // $
    MODERATE("$$"),     // $$
    EXPENSIVE("$$$"),   // $$$
    LUXURY("$$$$");     // $$$$

    private final String symbol;

    PriceRange(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}