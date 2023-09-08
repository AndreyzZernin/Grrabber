package com.example.vkGrubber.responcy;

import com.example.vkGrubber.model.VkGroup;

import java.util.List;

public interface RestTemplateVkBotApi {
    void saveGroupToApi(VkGroup vkGroup);
    List<VkGroup> getAllGroupsFromApi();
    String setCountToApi(int id , int count);
}
