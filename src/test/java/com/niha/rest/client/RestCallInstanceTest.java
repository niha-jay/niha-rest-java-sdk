package com.niha.rest.client;

import com.niha.rest.client.model.Person;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

// TODO -
public class RestCallInstanceTest {

    @Test
    public void postCallTest() {
        Person person = new Person();
        RestCallInstance<Person, Person> restCall = new RestCallInstance<>(new RestTemplate());
        ResponseMessage responseMessage = restCall.withUrl("Some url")
                .withHttpMethod(HttpMethod.POST)
                .withData(person)
                .post();

        responseMessage.getData();

    }
}
