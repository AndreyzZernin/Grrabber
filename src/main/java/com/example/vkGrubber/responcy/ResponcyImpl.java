package com.example.vkGrubber.responcy;

import com.example.vkGrubber.model.VkGroup;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


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
    public List<VkGroup> getAllGroupsFromApi() {

        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<List<VkGroup>> response = restTemplate.exchange(
                "http://localhost:8085/group/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VkGroup>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            List<VkGroup> groups = response.getBody();
            System.out.println("Received groups: " + groups);
            return groups;
        } else {
            System.out.println("Error retrieving groups from API. Status code: " + response.getStatusCodeValue());
            return Collections.emptyList();
        }
    }

    public String setCountToApi(int id , int count){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8085/group/setcount/"+ count + "/" + id;

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        HttpStatus status = (HttpStatus) responseEntity.getStatusCode();



        if (status == HttpStatus.OK) {
            return "ok";
        } else {
            return "bed";
        }
    }
}

