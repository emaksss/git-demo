package ru.mixart.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageService {
    ResponseEntity<Map> getImage(String tag);
}
