package com.luxoft.olshevchenko.movieland.entity.enums;

import java.util.Arrays;

/**
 * @author Oleksandr Shevchenko
 */
public enum Currencies {
    UAH, USD, EUR;

    public static Currencies currencyIgnoreCase (String currency) {
        return Arrays.stream(values())
                .filter(c -> c.name()
                .equalsIgnoreCase(currency))
                .findAny()
                .orElse(null);
    }

}
