package com.luxoft.olshevchenko.movieland.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.olshevchenko.movieland.entity.Currency;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Oleksandr Shevchenko
 */
@Service
public class CurrencyService {
    private final String path = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public double getCurrencyRateOnThisDay(String curr) {
        Currency[] currencyList = getCurrencies();
        System.out.println(Arrays.toString(currencyList));
        for (Currency currency : currencyList) {
            if (Objects.equals(curr, "USD") || Objects.equals(curr, "usd") && currency.getR030() == 840) {
                return currency.getRate();
            } else if (Objects.equals(curr, "EUR") || Objects.equals(curr, "eur") && currency.getR030() == 978) {
                return currency.getRate();
            }
        }
        return 0;
    }

    @SneakyThrows
    private Currency[] getCurrencies() {
        URL www = new URL(path);
        return objectMapper.readValue(www, Currency[].class);
    }
}
