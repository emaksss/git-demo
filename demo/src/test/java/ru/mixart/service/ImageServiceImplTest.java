package ru.mixart.service;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mixart.client.FeignImageClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("ru.mixart")
public class ImageServiceImplTest {

    @Autowired
    private ImageServiceImpl imageService;
    @MockBean
    private FeignImageClient imageClient;

    public void getImage() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap(), HttpStatus.OK);
        Mockito.when(imageClient.getRandomImage(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(testEntity);
        ResponseEntity<Map> result = imageService.getImage("zero");
        assertEquals("word", result.getBody().get("word"));


    }
}
