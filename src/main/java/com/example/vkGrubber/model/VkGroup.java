package com.example.vkGrubber.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class VkGroup {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id", columnDefinition = "INT")
    private int ownerId;
    @Column(name = "url")
    private String URL;
    @Column(name = "members",columnDefinition = "INT")
    private int members;
    @Column(name = "count", columnDefinition = "INT")
    private int count;

    @OneToMany
    private List<VKpost> vkPost;

//
    public VkGroup(int id , int ownerId, String name, Integer membersCount, String url) {
        this.id = Long.valueOf(id);
        this.ownerId = ownerId;
        this.name = name;
        this.members = membersCount;
        this.URL = "https://vk.com/" + url;
        this.count = 0;
    }

    public VkGroup(int id , int ownerId, String name, Integer membersCount, String url, int count) {
        this.id = Long.valueOf(id);
        this.ownerId = ownerId;
        this.name = name;
        this.members = membersCount;
        this.URL = "https://vk.com/" + url;
        this.count = count;
    }
    public VkGroup() {

    }
//    public void setCount(int count){
//        this.count = count;
//    }
}
