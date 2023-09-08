package com.example.vkGrubber.serviceImpl;

import com.example.vkGrubber.model.VKpost;
import com.example.vkGrubber.repository.VkAttachmentsRepo;
import com.example.vkGrubber.repository.VkPostRepo;
import com.example.vkGrubber.service.VkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VkPostServiceImpl implements VkPostService {

    @Autowired
    private VkPostRepo vkPostRepo;

    @Autowired
    private VkAttachmentsRepo vkAttachmentsRepo;



    @Override
    public List<VKpost> getVkPostForGroup(Long groupId, String status) {
        return vkPostRepo.findVkPostsByGroupIdAndStatus(groupId, status);
    }

    @Override
    public VKpost getVkPost( Long postId) {
        return vkPostRepo.findById(postId).get();
    }

    @Override
    public void saveVkPost(VKpost vKpost, Long groupId) {

        vkAttachmentsRepo.saveAll(vKpost.getVkAttachmentsList());
        vkPostRepo.save(vKpost);
    }

    @Override
    public String removeVkPost( Long postId) {
        Optional<VKpost> vKpostOptional = vkPostRepo.findById(postId);
        if (vKpostOptional.isPresent()) {
            vkPostRepo.delete(vKpostOptional.get());
            return "Удаленно";
        } else {
        return "Ненайдено";
        }

    }

    @Override
    public void setPostStatus(Long id, String status) {
        vkPostRepo.findById(id).ifPresent(post -> post.setStatus(status));
    }
}
