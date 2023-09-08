package com.example.vkGrubber.service;

import com.example.vkGrubber.model.VKpost;

import java.util.List;

public interface VkPostService {
    List<VKpost> getVkPostForGroup (Long groupId , String status);
    VKpost getVkPost(Long postId);
    void saveVkPost (VKpost vKpost ,  Long groupId);
    String removeVkPost ( Long postId);

    void setPostStatus ( Long id, String status);
}
