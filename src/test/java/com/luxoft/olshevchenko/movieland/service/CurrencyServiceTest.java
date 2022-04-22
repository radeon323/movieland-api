package com.luxoft.olshevchenko.movieland.service;

import com.luxoft.olshevchenko.movieland.entity.enums.Currencies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyService currencyService;

    @Test
    void testGetCurrencyRateOnThisDay() {
        Currencies currency = Currencies.USD;
        Mockito.when(currencyService.getCurrencyRateOnThisDay(currency)).thenReturn(22.33);
        double currencyRate = currencyService.getCurrencyRateOnThisDay(currency);
        assertEquals(22.33, currencyRate);
    }
}