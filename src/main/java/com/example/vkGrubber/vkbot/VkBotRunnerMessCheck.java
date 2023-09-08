package com.example.vkGrubber.vkbot;

import com.example.vkGrubber.model.VkGroup;
import com.example.vkGrubber.responcy.RestTemplateVkBotApiImpl;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Fields;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class VkBotRunnerMessCheck implements VkRun{
    RestTemplateVkBotApiImpl responcy = new RestTemplateVkBotApiImpl();

    @SneakyThrows
    @Override
    public void run() {
        String token = "vk1.a.DJuxouDIZEV6kyigUwBbdZnCI-sSmakenmpZYoscrj67j4YjpgHrUGsa-ly18_HjHpOiTtjPpYmGMuW4mcCQ1wke3peZXjGVQk0H3ThMwoDXQ_0npRUxZaNc2J5Z_7DmGzG2n8hLWFdICq4fyzdjU0lQLXTbfUhCRmZF1PdFybahhXxDGZvAy-zChuIvUv-9LlmBFYFJnymazUpyMzDZkg";
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        UserActor userActor = new UserActor(296590050, "vk1.a.ey6te-oP6CjTgCI_NZlqvWxVUPVEVmU8zpRF1br0I9IQU1IVhwBgJBnk4STGjTOZQ9LFuTdOg5Q63VrVNODW0N4Yh0zBXGaftQFgolE__noisPX_Vmn4hBPLGK-wUWRNsLjWwesJ6CuMpMmDvq039sLs4PUaiT8hzcyHoQE6W1ie4xf4_rU9xyiWkMvwBzcfVafDGIDLRG4NwWe_efycBw");
        GroupActor actor = new GroupActor(220104393, token);
        Integer ts = null;
        ts = vk.messages().getLongPollServer(actor).execute().getTs();

        while (true) {
            System.out.println("Обновление сообщений");
            Thread.sleep(10000);
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = null;
            messages = historyQuery.execute().getMessages().getItems();
                if (!messages.isEmpty()){
                    messages.forEach(message -> {
                        String s  =  message.getText();
                        System.out.println(s);
                        try {
                            Fields field = Fields.MEMBERS_COUNT;
                            vk.groups().getByIdObjectLegacy(userActor).groupId(s).fields(field).execute().forEach(group ->{
                                System.out.println(s);
                                VkGroup vkGroup = new VkGroup(group.getId(), group.getId(), group.getName(), group.getMembersCount(), group.getScreenName());
                                responcy.saveGroupToApi(vkGroup);
                            });
                        } catch (ApiException e) {
                            throw new RuntimeException(e);
                        } catch (ClientException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(message.toString());
                    });
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
                }
        }
    }
}
