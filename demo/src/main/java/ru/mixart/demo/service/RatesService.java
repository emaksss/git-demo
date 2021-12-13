package ru.mixart.demo.service;

import java.util.List;

public interface RatesService {
    void downloadRates();
    List<String> getCodes();
    int compareRates(String charCode);
}
