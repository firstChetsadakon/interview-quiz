package com.ascendcorp.interviewquiz.client;

import com.ascendcorp.interviewquiz.models.BotResponse;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class BotClient {

    @Qualifier("botWebClient")
    private final WebClient webClient;
    @Value("${bot.financial-institutions-holidays-uri}")
    private String uri;

    public BotClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<FinancialInstitutionsHolidaysData> getFinancialInstitutionsHoliday(int year) {
        log.info("call getFinancialInstitutionsHoliday: {}", webClient.get());
        String path = uri + "?year=" + year;
        BotResponse botResponse = webClient.get()
                .uri(path)
                .retrieve().bodyToMono(BotResponse.class)
                .block();
        log.info("botResponse: {}", botResponse);
        return botResponse.getResult().getData();
    }

}
