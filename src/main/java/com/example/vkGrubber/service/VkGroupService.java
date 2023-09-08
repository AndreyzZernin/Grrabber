package com.example.vkGrubber.service;

import com.example.vkGrubber.model.VkGroup;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface VkGroupService {

    List<VkGroup> getAllGroup();
    VkGroup getGroup(Long id);
    HttpStatus setGroup(VkGroup vkGroup);
    String removeGroup (Long id);
    void updateGroup (VkGroup vkGroup);
}
