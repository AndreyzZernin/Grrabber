package com.example.vkGrubber.responcy;

import com.example.vkGrubber.model.VkGroup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ResponcyImpl {
    public void saveGroupToApi(VkGroup vkGroup) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VkGroup> requestEntity = new HttpEntity<>(vkGroup, headers);


        ResponseEntity<VkGroup> response = restTemplate.exchange(
                "http://localhost:8085/group/save",
                HttpMethod.POST,
                requestEntity,
                VkGroup.class
        );


    }
}

