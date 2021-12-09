package ru.mixart.client;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageClient {
    ResponseEntity<Map> getRandomImage(String apiKey, String tag,String rating);
}
