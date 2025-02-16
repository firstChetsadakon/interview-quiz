package com.ascendcorp.interviewquiz.client;

import com.ascendcorp.interviewquiz.models.BotResponse;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BotClientTest {

    @Mock
    private WebClient webClient;

    @Value("${bot.financial-institutions-holidays-uri}")
    private String uri;

    @InjectMocks
    private BotClient botClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        ReflectionTestUtils.setField(botClient, "uri", "/api/financial-institutions-holidays");

        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
    }

    @Test
    public void testGetFinancialInstitutionsHoliday_Success() {
        int year = 2025;

        BotResponse mockResponse = new BotResponse();
        FinancialInstitutionsHolidaysResponse resultResponse = new FinancialInstitutionsHolidaysResponse();
        List<FinancialInstitutionsHolidaysData> expectedHolidays = Arrays.asList(
                new FinancialInstitutionsHolidaysData("Wednesday", "วันพุธ", "2025-01-01", "01/01/2568", "New Year's Day", "วันขึ้นปีใหม่"),
                new FinancialInstitutionsHolidaysData("Monday", "วันจันทร์", "2025-04-13", "13/04/2568", "Songkran Festival", "วันสงกรานต์")
        );
        resultResponse.setData(expectedHolidays);
        mockResponse.setResult(resultResponse);

        when(responseSpec.bodyToMono(BotResponse.class))
                .thenReturn(Mono.just(mockResponse));


        List<FinancialInstitutionsHolidaysData> result = botClient.getFinancialInstitutionsHoliday(year);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("New Year's Day", result.get(0).getHolidayDescription());
        assertEquals("Songkran Festival", result.get(1).getHolidayDescription());

        verify(webClient).get();
        verify(requestHeadersUriSpec).uri("/api/financial-institutions-holidays?year=2025");
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(BotResponse.class);
    }

    @Test
    public void testGetFinancialInstitutionsHoliday_EmptyResponse() {

        int year = 2025;

        BotResponse mockResponse = new BotResponse();
        FinancialInstitutionsHolidaysResponse resultResponse = new FinancialInstitutionsHolidaysResponse();
        resultResponse.setData(Collections.emptyList());
        mockResponse.setResult(resultResponse);

        when(responseSpec.bodyToMono(BotResponse.class))
                .thenReturn(Mono.just(mockResponse));


        List<FinancialInstitutionsHolidaysData> result = botClient.getFinancialInstitutionsHoliday(year);


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testGetFinancialInstitutionsHoliday_Error() {

        int year = 2025;

        when(responseSpec.bodyToMono(BotResponse.class))
                .thenReturn(Mono.error(new RuntimeException("API Error")));


        botClient.getFinancialInstitutionsHoliday(year);
    }
}
