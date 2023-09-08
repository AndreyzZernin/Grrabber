package com.example.vkGrubber.controller;

import com.example.vkGrubber.model.VkGroup;
import com.example.vkGrubber.service.VkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class VkGroupController {

    @Autowired
    private VkGroupService vkGroupService;

    @GetMapping("/all")
    public List<VkGroup> getVkGroups(){

        return vkGroupService.getAllGroup();
    }

    @GetMapping("/{id}")
    public VkGroup getVkGroup(@PathVariable Long id){
       return vkGroupService.getGroup(id);
    }

    @PostMapping("/save")
    public ResponseEntity<VkGroup> saveGroup(@RequestBody VkGroup vkGroup){
        HttpStatus hs =  vkGroupService.setGroup(vkGroup);
        return new ResponseEntity<>(vkGroup, hs);
    }
    @GetMapping("/setcount/{count}/{id}")
    public ResponseEntity<String> setCount(@PathVariable int count, @PathVariable long id){
        VkGroup vkGroup = vkGroupService.getGroup(id);
        vkGroup.setCount(count);
        vkGroupService.updateGroup(vkGroup);
        return ResponseEntity.ok("answer");
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id){
        String answer = vkGroupService.removeGroup(id);
        return ResponseEntity.ok(answer);
    }


}
