package com.ascendcorp.interviewquiz.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(MockitoJUnitRunner.class)
public class BotClientTest {

    private WebClient webClient;

    private BotClient botClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    //TODO Implement unit test for BotClient
}