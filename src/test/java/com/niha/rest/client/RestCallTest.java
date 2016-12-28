package com.niha.rest.client;

import com.niha.rest.client.model.Notification;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

// TODO -
public class RestCallTest {

    @Test @Ignore
    public void postCallTest() {

        final String apiKey = "your Firebase server api key";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "key=" + apiKey);
        headers.add("Content-Type", "application/json");

        Notification notification = new Notification();
        notification.setTo("/topics/device_notification");
        Map<String, String> message = new HashMap<>();
        message.put("message", "2017 is year Niha");
        notification.setData(message);

        ResponseMessage responseMessage = RestCall
                .builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .headers(headers)
                .data(notification)
                .build()
                .post();
    }
}
