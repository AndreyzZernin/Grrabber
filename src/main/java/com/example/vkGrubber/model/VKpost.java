package com.example.vkGrubber.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class VKpost {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;

    private String URL;

    private Date date;

    private String text;

    private String status;

    @OneToMany
    private List<VkAttachments> vkAttachmentsList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="vk_group_id")
    private VkGroup vkGroup;
}
