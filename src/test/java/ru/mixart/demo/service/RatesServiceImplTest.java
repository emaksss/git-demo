package ru.mixart.demo.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mixart.demo.client.FeignRatesClient;
import ru.mixart.demo.model.Rates;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ComponentScan("ru.mixart.demo")
class RatesServiceImplTest {

    @Autowired
    private RatesServiceImpl RatesService;
    @MockBean
    private FeignRatesClient ratesClient;

    private Rates currentRates;
    private Rates historicalRates;


@BeforeEach
    public void init() {
        int timestamp = 1639130400;
        this.currentRates = new Rates();
        this.currentRates.setTimestamp(timestamp);
        this.currentRates.setBase("USD");
        Map<String, Double> currentRatesMap = new HashMap<>();
        currentRatesMap.put("AED", 3.672951);
        currentRatesMap.put("AFN", 97.949996);
        currentRatesMap.put("ALL", 107.165934);
        currentRatesMap.put("USD", 1.0);
        this.currentRates.setRates(currentRatesMap);

        timestamp = 1638460800;
        this.historicalRates = new Rates();
        this.historicalRates.setTimestamp(timestamp);
        this.historicalRates.setBase("USD");
        Map<String, Double> historicalRatesMap = new HashMap<>();
        historicalRatesMap.put("AED", 3.673);
        historicalRatesMap.put("AFN", 98.146917);
        historicalRatesMap.put("ALL", 106.873457);
        historicalRatesMap.put("USD", 1.0);
        this.historicalRates.setRates(historicalRatesMap);

    }
    @Test
    public void whenPositiveCompare() {
        Mockito.when(ratesClient.getRates(anyString(), anyString()))
                .thenReturn(this.currentRates);

        Mockito.when(ratesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(this.historicalRates);
        int result = RatesService.compareRates("ALL");
        assertEquals(1, result);
    }

    @Test
    public void whenNegativeCompare() {
        Mockito.when(ratesClient.getRates(anyString(), anyString()))
                .thenReturn(this.currentRates);
        Mockito.when(ratesClient.getHistoricalRates(anyString(), anyString(), anyString()))
                .thenReturn(this.historicalRates);
        int result = RatesService.compareRates("AFN");
        assertEquals(-1, result);
    }


}