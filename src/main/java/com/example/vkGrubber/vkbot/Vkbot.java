package com.example.vkGrubber.vkbot;

import org.springframework.stereotype.Component;

@Component
public class Vkbot {



    static  {

        Runnable VkBotRunner = new VkBotRunner();
        Thread thread = new Thread(VkBotRunner);
        thread.start();
    }
}
