package com.luxoft.olshevchenko.movieland.entity.enums;

import java.util.Arrays;

/**
 * @author Oleksandr Shevchenko
 */
public enum Order {
    ASC, DESC;

    public static Order ignoreCase (String order) {
        return Arrays.stream(values())
                .filter(c -> c.name()
                .equalsIgnoreCase(order))
                .findAny()
                .orElse(null);
    }
}
