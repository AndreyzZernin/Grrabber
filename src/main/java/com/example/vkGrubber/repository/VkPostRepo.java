package com.example.vkGrubber.repository;

import com.example.vkGrubber.model.VKpost;
import com.example.vkGrubber.model.VkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface VkPostRepo extends JpaRepository<VKpost,Long> {
    @Query(value = "SELECT p FROM VKpost p WHERE p.vkGroup.id = :groupId AND p.status = :status")
    List<VKpost> findVkPostsByGroupIdAndStatus(@Param("groupId") Long groupId, @Param("status") String status);

    List<VKpost> findByStatus(String status);
    List<VKpost> findByVkGroup(VkGroup vkGroup);
}
