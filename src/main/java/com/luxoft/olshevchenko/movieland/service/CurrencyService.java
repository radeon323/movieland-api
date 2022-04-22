package com.luxoft.olshevchenko.movieland.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.olshevchenko.movieland.entity.enums.Currencies;
import com.luxoft.olshevchenko.movieland.entity.Currency;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * @author Oleksandr Shevchenko
 */
@Service
public class CurrencyService {
    ObjectMapper objectMapper = new ObjectMapper();
    private static final String path = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @SneakyThrows
    public double getCurrencyRateOnThisDay(Currencies currencies) {
        Currency[] currencyList = getCurrencies();
        for (Currency currency : currencyList) {
            if (Currencies.USD == currencies && currency.getR030() == 840) {
                return currency.getRate();
            } else if (Currencies.EUR == currencies && currency.getR030() == 978) {
                return currency.getRate();
            }
        }
        return 0;
    }

    @SneakyThrows
    private Currency[] getCurrencies() {
        return objectMapper.readValue(new URL(path), Currency[].class);
    }
}
