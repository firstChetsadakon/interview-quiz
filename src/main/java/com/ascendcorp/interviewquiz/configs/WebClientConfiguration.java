
package com.ascendcorp.interviewquiz.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${bot.host}")
    private String botHost;

    @Value("${bot.api-key}")
    private String apiKey;

    @Bean("botWebClient")
    public WebClient webClient() {
        return WebClient
            .builder()
            .baseUrl(botHost)
                .defaultHeader("X-IBM-Client-Id", apiKey)
                .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
