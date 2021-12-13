package ru.mixart.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mixart.demo.client.RatesClient;
import ru.mixart.demo.model.Rates;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatesServiceImpl implements RatesService {
    private Rates currentRates;
    private Rates historicalRates;
    private SimpleDateFormat dateFormat;
    @Value("${rates.appid}")
    private String appId;
    @Value("${rates.base}")
    private String base;
    private RatesClient ratesClient;

    @Autowired
    public RatesServiceImpl(
            RatesClient ratesClient) {
        this.ratesClient = ratesClient;

    }

    @Override
    public void downloadRates() {
        this.currentRates = ratesClient.getRates(this.appId, this.base);
        this.historicalRates = ratesClient.getHistoricalRates(String.valueOf(LocalDate.now().minusDays(1)),this.appId, this.base);
    }

    @Override
    public List<String> getCodes() {
        this.downloadRates();
        List<String> result = null;
        if (this.currentRates.getRates() != null) {
            result = new ArrayList<>(this.currentRates.getRates().keySet());

        }
        return result;
    }

    @Override
    public int compareRates(String el) {
        if (this.currentRates== null){this.downloadRates();}
        Double rate1 = this.currentRates.getRates().get(el);
        Double rate2 = this.historicalRates.getRates().get(el);

        //System.out.println(rate1+" "+rate2);
        //System.out.println(Double.compare(rate1, rate2));


        return Double.compare(rate1, rate2);
    }
}
