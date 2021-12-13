package ru.mixart.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//https://api.giphy.com/v1/gifs/random?api_key=pjbfiw64WQEcu8b2Mm8fbeEDxWwSO3t0&tag=rich&rating=g
@FeignClient(name = "imageClient", url = "${image.url}")
public interface FeignImageClient extends ImageClient{

    @Override
    @GetMapping("/random")
    ResponseEntity<Map> getRandomImage(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag,
            @RequestParam("rating") String rating

    );
}