package com.example.vkGrubber.serviceImpl;

import com.example.vkGrubber.model.VkGroup;
import com.example.vkGrubber.repository.VkGroupRepo;
import com.example.vkGrubber.service.VkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VkGroupServiceImpl implements VkGroupService {
    @Autowired
    VkGroupRepo vkGroupRepo;


    @Override
    public List<VkGroup> getAllGroup() {
        return vkGroupRepo.findAll();
    }

    @Override
    public VkGroup getGroup(Long id) {
        Optional<VkGroup> vkGroupOptional= vkGroupRepo.findById(id);
        return vkGroupOptional.get();

    }

    @Override
    public HttpStatus setGroup(VkGroup vkGroup) {
        if (vkGroupRepo.findById(vkGroup.getId()).isPresent()){
            return HttpStatus.BAD_REQUEST;
        }
        vkGroupRepo.save(vkGroup);
        return HttpStatus.OK;
    }

    @Override
    public String removeGroup(Long id) {
        if (vkGroupRepo.findById(id).isPresent()) {
            vkGroupRepo.delete(vkGroupRepo.findById(id).get());
            return "Успешно удален";}
        return "Не найдена группа";
        }

    @Override
    public void updateGroup(VkGroup vkGroup) {
            vkGroupRepo.save(vkGroup);
    }
}
