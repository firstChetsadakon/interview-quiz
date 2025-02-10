package com.ascendcorp.interviewquiz.client;

import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BotClient {

    private final Logger logger = LoggerFactory.getLogger(BotClient.class);
    @Qualifier("botWebClient")
    private final WebClient webClient;

    public BotClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<FinancialInstitutionsHolidaysData> getFinancialInstitutionsHoliday() {
        logger.info("call getFinancialInstitutionsHoliday: {}");
        //TODO Implement method to get holiday data by use Webclient

        logger.info("botResponse: {}");
        return Collections.emptyList();
    }

}
