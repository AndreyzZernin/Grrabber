package com.example.vkGrubber.controller;

import com.example.vkGrubber.model.VKpost;
import com.example.vkGrubber.service.VkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class VkPostController {
    @Autowired
    VkPostService vkPostService;

    @PostMapping("/save/{id}")
    public ResponseEntity<VKpost> saveVkPost(@RequestBody VKpost vKpost, @PathVariable Long id){

        vkPostService.saveVkPost(vKpost, id);
        return ResponseEntity.ok(vKpost);
    }
    @GetMapping("/getStatus/{groupId}")
    public List<VKpost> getVkPostForStatus(@PathVariable Long groupId, @RequestParam String status){
        return vkPostService.getVkPostForGroup(groupId, status);
    }
    @GetMapping("/updatestatus/{id}")
    public ResponseEntity<VKpost> updateStatus(@PathVariable Long id,  @RequestParam String status){
        vkPostService.setPostStatus(id, status);
        return ResponseEntity.ok(vkPostService.getVkPost(id));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<VKpost> getPost(@PathVariable Long id){
        return ResponseEntity.ok(vkPostService.getVkPost(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        String answer = vkPostService.removeVkPost(id);
        return ResponseEntity.ok(answer);
    }
}
