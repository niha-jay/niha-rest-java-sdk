package com.niha.rest.client;

import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Getter
public class RestCallInstance<D, R> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestCallInstance.class);

    private RestTemplate restTemplate;
    private String url;
    private HttpMethod httpMethod;
    private D data;
    private Class<R> type;

    public RestCallInstance(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestCallInstance withUrl(String url) {
        this.url = url;
        return this;
    }

    public RestCallInstance withHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RestCallInstance withData(D data) {
        this.data = data;
        return this;
    }

    public RestCallInstance withResponseType(Class<R> type) {
        this.type = type;
        return this;
    }

    public ResponseMessage<R> post() {
        LOG.info("REST post method call");
        HttpEntity<D> entity = new HttpEntity(data);

        ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.POST, entity, type);

        return ResponseMessage.of(response);
    }

    public <D, R> ResponseMessage<R> postEntity(String url, D postData, Class<R> type) {
        LOG.info("REST post method call");
        HttpEntity<D> entity = new HttpEntity(postData);

        ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.POST, entity, type);

        return ResponseMessage.of(response);
    }
}
