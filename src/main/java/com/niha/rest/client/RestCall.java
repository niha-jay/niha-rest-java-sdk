package com.niha.rest.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Getter @Builder
public class RestCall<D, R> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestCall.class);

    private RestTemplate restTemplate;
    private String url;
    private HttpMethod httpMethod;
    private D data;
    private Class<R> responseEntityType;
    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

    public RestCall withRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        return this;
    }

    public RestCall withUrl(String url) {
        this.url = url;
        return this;
    }

    public RestCall withHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RestCall withData(D data) {
        this.data = data;
        return this;
    }

    public RestCall withHeader(String key, String value) {
        headers.add(key, value);
        return this;
    }

    public RestCall withParam(String key, String value) {
        params.add(key, value);
        return this;
    }

    public RestCall withBodyParams(String key, String value) {
        body.add(key, value);
        return this;
    }

    public RestCall withResponseType(Class<R> type) {
        this.responseEntityType = type;
        return this;
    }

    public ResponseMessage<R> post() {
        LOG.info("REST post method call");
        HttpEntity<D> entity = new HttpEntity(data, headers);

        Objects.requireNonNull(url);
        return ResponseMessage
                .of(restTemplate
                        .exchange(url, HttpMethod.POST, entity, responseEntityType));
    }

    public ResponseMessage<R> postWithFormData() {
        LOG.info("REST post method call of content-type application/x-www-form-urlencoded");
        HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

        return ResponseMessage
                .of(restTemplate
                        .exchange(url, HttpMethod.POST, httpEntity, responseEntityType));
    }

    public static RestCall.Builder builder() {
        return new RestCall.Builder();
    }

    public void initClient() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);

        restTemplate = new RestTemplateBuilder().build();
    }

    public static class Builder extends RestCallBuilder {

        Builder() {
            super();
        }

        @Override
        public RestCall build() {
            // Implement SSL
            RestCall restCall = super.build();
            try {
                restCall.initClient();
            } catch (Exception e) {
                LOG.error("Problem occurred creating the Rest Client: Make sure to set the correct ");
                throw new IllegalStateException("Problem occurred creating the Rest Client: Make sure to set the correct ");
            }
            return restCall;
        }
    }
}
