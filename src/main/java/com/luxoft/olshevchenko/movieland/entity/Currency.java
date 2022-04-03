package com.luxoft.olshevchenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Oleksandr Shevchenko
 */
@Getter
@ToString
@RequiredArgsConstructor
public class Currency {

    @JsonProperty("r030")
    private int r030;

    @JsonProperty("txt")
    private String txt;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("cc")
    private String cc;

    @JsonProperty("exchangedate")
    private String exchangeDate;

}


// "r030":840,"txt":"Долар США","rate":29.2549,"cc":"USD","exchangedate":"04.04.2022"