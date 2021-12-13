package ru.mixart.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mixart.demo.client.FeignImageClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ComponentScan("ru.mixart.demo")
class ImageServiceImplTest {

    @Autowired
    private ImageServiceImpl imageService;

    @MockBean
    private FeignImageClient imageClient;

    @Test
    void getImage() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap(), HttpStatus.OK);
        Mockito.when(imageClient.getRandomImage(anyString(), anyString(), anyString()))
                .thenReturn(testEntity);
        ResponseEntity<Map> result = imageService.getImage("zero");
        assertEquals("zero", result.getBody().get("word"));
    }
}