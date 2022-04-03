package com.luxoft.olshevchenko.movieland.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.olshevchenko.movieland.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Objects;

/**
 * @author Oleksandr Shevchenko
 */
@Service
@AllArgsConstructor
public class CurrencyService {

    @SneakyThrows
    public double getCurrencyRateOnThisDay(String curr) {
        ObjectMapper objectMapper = new ObjectMapper();
        URL www = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        Currency[] currencyList = objectMapper.readValue(www, Currency[].class);
        for (Currency currency : currencyList) {
            if (Objects.equals(curr, "USD") || Objects.equals(curr, "usd") && currency.getR030() == 840) {
                return currency.getRate();
            } else if (Objects.equals(curr, "EUR") || Objects.equals(curr, "eur") && currency.getR030() == 978) {
                return currency.getRate();
            }
        }
        return 0;
    }
}
