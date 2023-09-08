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
        // Инициализация макетов и внедрение их в сервис
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGroup() {
        // Создание макета репозитория
        List<VkGroup> groups = new ArrayList<>();
        groups.add(new VkGroup(1L, "Group 1"));
        groups.add(new VkGroup(2L, "Group 2"));

        // Настройка поведения макета репозитория
        when(vkGroupRepo.findAll()).thenReturn(groups);

        // Вызов метода, который мы тестируем
        List<VkGroup> result = vkGroupService.getAllGroup();

        // Проверка ожидаемого результата
        assertEquals(groups, result);

        // Убедитесь, что метод findAll() у макета репозитория был вызван один раз
        verify(vkGroupRepo, times(1)).findAll();
    }

    @Test
    void testGetGroup() {
        // Создание макета репозитория
        VkGroup group = new VkGroup(1L, "Group 1");

        // Настройка поведения макета репозитория
        when(vkGroupRepo.findById(1L)).thenReturn(Optional.of(group));

        // Вызов метода, который мы тестируем
        VkGroup result = vkGroupService.getGroup(1L);

        // Проверка ожидаемого результата
        assertEquals(group, result);

        // Убедитесь, что метод findById() у макета репозитория был вызван один раз с аргументом 1L
        verify(vkGroupRepo, times(1)).findById(1L);
    }

    @Test
    void testSetGroup() {
        // Создание макета репозитория
        when(vkGroupRepo.findById(1L)).thenReturn(Optional.empty());

        // Создание объекта VkGroup
        VkGroup group = new VkGroup(1L, "Group 1");

        // Вызов метода, который мы тестируем
        HttpStatus result = vkGroupService.setGroup(group);

        // Проверка ожидаемого результата
        assertEquals(HttpStatus.OK, result);

        // Убедитесь, что метод save() у макета репозитория был вызван один раз с аргументом group
        verify(vkGroupRepo, times(1)).save(group);
    }

    @Test
    void testRemoveGroup() {
        // Создание макета репозитория
        VkGroup group = new VkGroup(1L, "Group 1");
        when(vkGroupRepo.findById(1L)).thenReturn(Optional.of(group));

        // Вызов метода, который мы тестируем
        String result = vkGroupService.removeGroup(1L);

        // Проверка ожидаемого результата
        assertEquals("Успешно удален", result);

        // Убедитесь, что метод delete() у макета репозитория был вызван один раз с аргументом group
        verify(vkGroupRepo, times(1)).delete(group);
    }
}
