package com.niha.rest.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public enum JSON {

    MAPPER;

    private ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(JSON.class);

    JSON() {
        mapper = new ObjectMapper();
    }

    public byte[] toBytes(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (IOException e) {
            logger.error("Unable to transform object to byte[]", e);
            throw new IllegalArgumentException("Unable to transform object to byte[]");
        }
    }

    public <T> T toObject(byte[] payload, Class<T> type) {
        try {
            return mapper.readValue(payload, type);
        } catch (IOException e) {
            logger.error(String.format("Unable to transform byte[] to given type %s", type.getName()), e);
            throw new IllegalArgumentException(String.format("Unable to transform byte[] to given type %s", type.getName()));
        }
    }
}
