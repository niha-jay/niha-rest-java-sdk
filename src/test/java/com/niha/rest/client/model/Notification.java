package com.niha.rest.client.model;

import java.util.Map;

public class Notification {
    private Map<String, String> data;
    private String to;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
