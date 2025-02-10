
package com.ascendcorp.interviewquiz.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClienttConfiguration {

    @Value("${bot.host}")
    private String botHost;

    @Bean("botWebClient")
    public WebClient webClient() {
        return WebClient
            .builder()
            .baseUrl(botHost)
            .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
