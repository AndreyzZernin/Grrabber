package com.example.vkGrubber.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class VkGroup {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String URL;

    private int members;

    @OneToMany
    private List<VKpost> vkPost;


    public VkGroup(long l, String s) {
    }

    public VkGroup() {

    }
}
