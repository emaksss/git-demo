package ru.mixart.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mixart.demo.model.Rates;

// https://openexchangerates.org/api/latest.json?base=USD&app_id=c7501d7906f240a2afca4a127ff2b270
// https://openexchangerates.org/api/historical/2021-12-02.json?base=USD&app_id=c7501d7906f240a2afca4a127ff2b270
@FeignClient(name = "ratesClient", url = "${rates.url}")
public interface FeignRatesClient extends RatesClient{
    @Override
    @GetMapping("/latest.json")
    Rates getRates(@RequestParam("app_id") String appId, @RequestParam("base") String base);

    @Override
    @GetMapping("/historical/{date}.json")
    Rates getHistoricalRates(@PathVariable String date, @RequestParam("app_id") String appId, @RequestParam("base") String base);
}
