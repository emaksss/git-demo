package ru.mixart.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mixart.demo.client.ImageClient;

import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageClient imageClient;
    private RatesService ratesService;
    @Value("${image.apikey}")
    private String apiKey;
    @Value("${image.rating}")
    private String rating;


    @Autowired
    public ImageServiceImpl(ImageClient imageClient) {
        this.imageClient = imageClient;
    }
    @Override
    public ResponseEntity<Map> getImage(String val) {
        ResponseEntity<Map> result = imageClient.getRandomImage(this.apiKey, val, rating);
        result.getBody().put("word", val);
        return result;
    }

}
