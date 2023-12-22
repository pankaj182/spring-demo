package com.neatcode.serviceRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SampleAdapter {

    private final RestTemplate restTemplate;

    @Autowired
    public SampleAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getSomething() {
        String url = "http://ANOTHER-APP-NAME/users";
        String res = restTemplate.getForObject(url, String.class);
    }
}
