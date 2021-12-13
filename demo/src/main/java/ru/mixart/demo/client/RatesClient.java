package ru.mixart.demo.client;

import ru.mixart.demo.model.Rates;

public interface RatesClient {

    Rates getRates(String appId, String base);

    Rates getHistoricalRates(String date, String appId, String base);
}
