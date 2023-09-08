package com.example.vkGrubber.service;

import com.example.vkGrubber.model.VkGroup;
import com.example.vkGrubber.repository.VkGroupRepo;
import com.example.vkGrubber.serviceImpl.VkGroupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class VkGroupServiceImplTest {
    @Mock
    private VkGroupRepo vkGroupRepo;

    @InjectMocks
    private VkGroupServiceImpl vkGroupService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGroup() {

        List<VkGroup> groups = new ArrayList<>();
        groups.add(new VkGroup(1L, 1,"Group 1", 1000, "url1"));
        groups.add(new VkGroup(2L, 2,"Group 2",10000, "url2"));


        when(vkGroupRepo.findAll()).thenReturn(groups);


        List<VkGroup> result = vkGroupService.getAllGroup();

        assertEquals(groups, result);


        verify(vkGroupRepo, times(1)).findAll();
    }

    @Test
    void testGetGroup() {

        VkGroup group = new VkGroup(1L, 1,"Group 1", 1000, "url1");

        when(vkGroupRepo.findById(1L)).thenReturn(Optional.of(group));

        VkGroup result = vkGroupService.getGroup(1L);

        assertEquals(group, result);


        verify(vkGroupRepo, times(1)).findById(1L);
    }

    @Test
    void testSetGroup() {

        when(vkGroupRepo.findById(1L)).thenReturn(Optional.empty());

        VkGroup group = new VkGroup(1L, 1,"Group 1", 1000, "url1");

        HttpStatus result = vkGroupService.setGroup(group);

        assertEquals(HttpStatus.OK, result);

        verify(vkGroupRepo, times(1)).save(group);
    }

    @Test
    void testRemoveGroup() {

        VkGroup group = new VkGroup(1L, 1,"Group 1", 1000, "url1");
        when(vkGroupRepo.findById(1L)).thenReturn(Optional.of(group));

        String result = vkGroupService.removeGroup(1L);

        assertEquals("Успешно удален", result);

        verify(vkGroupRepo, times(1)).delete(group);
    }
}
