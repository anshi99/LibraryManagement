package com.nagarro.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class WebClientService {

    public WebClient getWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8090/api").build();
    }
}
