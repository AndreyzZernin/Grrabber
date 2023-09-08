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

    private int ownerId;

    private String name;

    private String URL;

    private int members;

    @OneToMany
    private List<VKpost> vkPost;


    public VkGroup() {

    }

    public VkGroup(long id , int ownerId, String name, Integer membersCount, String url) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.members = membersCount;
        this.URL = "https://vk.com/" + url;
    }
}
