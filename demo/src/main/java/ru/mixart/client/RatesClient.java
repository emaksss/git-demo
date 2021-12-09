package ru.mixart.client;

import ru.mixart.model.Rates;

public interface RatesClient {

    Rates getRates(String appId, String base);
    Rates getHistoricalRates(String date, String appId, String base);
}
