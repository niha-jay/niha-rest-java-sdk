package com.niha.rest.client;

import lombok.Builder;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@Builder
public class RestBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestBuilder.class);

    private RestTemplate restTemplate;


}
