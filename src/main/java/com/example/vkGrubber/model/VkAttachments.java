package com.example.vkGrubber.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VkAttachments {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String URL;

    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vk_post_id")
    private  VKpost vKpost;
}
