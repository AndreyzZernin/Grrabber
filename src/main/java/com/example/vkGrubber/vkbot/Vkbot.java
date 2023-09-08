package com.example.vkGrubber.vkbot;

import org.springframework.stereotype.Component;

@Component
public class Vkbot {


    private static final Runnable VkBotGrubber = new VkBotGrubber();

    static  {

        Runnable VkBotRunner = new VkBotRunnerMessCheck();
        Thread thread = new Thread(VkBotRunner);
        Thread thread1 = new Thread(VkBotGrubber);
        thread.start();
        thread1.start();
    }
}
