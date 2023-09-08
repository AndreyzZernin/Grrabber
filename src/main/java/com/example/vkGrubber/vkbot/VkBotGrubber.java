package com.example.vkGrubber.vkbot;

import com.example.vkGrubber.responcy.RestTemplateVkBotApi;
import com.example.vkGrubber.responcy.RestTemplateVkBotApiImpl;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VkBotGrubber implements VkRun{

    RestTemplateVkBotApi responcy = new RestTemplateVkBotApiImpl();

    @SneakyThrows
    @Override
    public void run() {
        String token = "vk1.a.DJuxouDIZEV6kyigUwBbdZnCI-sSmakenmpZYoscrj67j4YjpgHrUGsa-ly18_HjHpOiTtjPpYmGMuW4mcCQ1wke3peZXjGVQk0H3ThMwoDXQ_0npRUxZaNc2J5Z_7DmGzG2n8hLWFdICq4fyzdjU0lQLXTbfUhCRmZF1PdFybahhXxDGZvAy-zChuIvUv-9LlmBFYFJnymazUpyMzDZkg";
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor userActor = new UserActor(296590050, "vk1.a.ey6te-oP6CjTgCI_NZlqvWxVUPVEVmU8zpRF1br0I9IQU1IVhwBgJBnk4STGjTOZQ9LFuTdOg5Q63VrVNODW0N4Yh0zBXGaftQFgolE__noisPX_Vmn4hBPLGK-wUWRNsLjWwesJ6CuMpMmDvq039sLs4PUaiT8hzcyHoQE6W1ie4xf4_rU9xyiWkMvwBzcfVafDGIDLRG4NwWe_efycBw");
        GroupActor actor = new GroupActor(220104393, token);
        while (true) {
            Thread.sleep(10000);
            System.out.println("ищем посты");
            responcy.getAllGroupsFromApi().forEach(vkGroup -> {
                try {

                    GetResponse getResponse = vk.wall().get(userActor).ownerId(Math.toIntExact((vkGroup.getOwnerId())* -1)).count(1).offset(1).execute();
                    JSONObject json = (JSONObject) new JSONParser().parse(getResponse.toString());
                    System.out.println(json.get("count"));
                    if (vkGroup.getCount() != Integer.parseInt(json.get("count").toString())){
                        System.out.println("не равно");
                        responcy.setCountToApi(Math.toIntExact(vkGroup.getId()),Integer.parseInt(json.get("count").toString()));
                    }
                } catch (ApiException | ClientException | ParseException e) {
                    throw new RuntimeException(e);
                }

            });

        }
    }
}
