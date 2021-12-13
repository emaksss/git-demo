package ru.mixart.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mixart.demo.service.ImageService;
import ru.mixart.demo.service.RatesService;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private RatesService ratesService;
    private ImageService imageService;

    @Autowired
    public MainController(RatesService ratesService,ImageService imageService) {

        this.ratesService = ratesService;
        this.imageService = imageService;
    }

    @GetMapping("/getcodes")
    public List<String> getCodes() {
        return ratesService.getCodes();
    }

    @GetMapping("/getimage/{val}")
    public ResponseEntity<Map> getImage(@PathVariable String val) {
        int chooser = 0;
        String tag;
        if (val != null) {
            chooser = ratesService.compareRates(val);
        }
        if (chooser==1){tag="rich";}
        else if(chooser==0){tag="zero";}
        else{tag="broke";}
        ResponseEntity<Map> result = null;
        System.out.println(val);
        result = imageService.getImage(tag);
        return result;

    }
}
