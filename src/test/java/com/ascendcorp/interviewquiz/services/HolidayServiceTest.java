package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.client.BotClient;
import com.ascendcorp.interviewquiz.exceptions.ExternalApiException;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.ascendcorp.interviewquiz.validators.HolidayValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HolidayServiceTest {

    @Mock
    private BotClient botClient;

    @Mock
    private HolidayValidator holidayValidator;

    @InjectMocks
    private HolidayService holidayService;

    private List<FinancialInstitutionsHolidaysData> mockHolidays;

    @Before // ใช้ @Before สำหรับ JUnit 4 (แทน @BeforeEach ของ JUnit 5)
    public void init() {
        FinancialInstitutionsHolidaysData h1 = new FinancialInstitutionsHolidaysData();
        h1.setDate(String.valueOf(LocalDate.now()));
        h1.setHolidayDescription("Today is holiday");

        FinancialInstitutionsHolidaysData h2 = new FinancialInstitutionsHolidaysData();
        h2.setDate(String.valueOf(LocalDate.now().plusDays(10)));
        h2.setHolidayDescription("Future holiday");

        mockHolidays = Arrays.asList(h1, h2);
    }

    @Test
    public void testGetHoliday_ValidYear() {
        int validYear = 2025;
        when(botClient.getFinancialInstitutionsHoliday(validYear)).thenReturn(mockHolidays);

        List<FinancialInstitutionsHolidaysData> result = holidayService.getHoliday(String.valueOf(validYear));

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(holidayValidator).validateYear(validYear);
        Mockito.verify(botClient).getFinancialInstitutionsHoliday(validYear);
    }

    @Test
    public void testGetHoliday_NullYear() {
        int currentYear = LocalDate.now().getYear();
        when(botClient.getFinancialInstitutionsHoliday(currentYear)).thenReturn(mockHolidays);

        List<FinancialInstitutionsHolidaysData> result = holidayService.getHoliday(null);

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(holidayValidator).validateYear(currentYear);
        Mockito.verify(botClient).getFinancialInstitutionsHoliday(currentYear);
    }

    @Test
    public void testGetHoliday_InvalidYearFormat() {
        assertThrows(IllegalArgumentException.class, () -> holidayService.getHoliday("abc"));
    }

    @Test
    public void testGetHoliday_NegativeYear() {
        int invalidYear = -1;
        Mockito.doThrow(new IllegalArgumentException("Year cannot be negative"))
                .when(holidayValidator).validateYear(invalidYear);

        assertThrows(IllegalArgumentException.class, () -> holidayService.getHoliday(String.valueOf(invalidYear)));
    }

    @Test
    public void testGetHoliday_ExternalApiReturnsNull() {
        int validYear = 2026;
        when(botClient.getFinancialInstitutionsHoliday(validYear)).thenReturn(null);

        assertThrows(ExternalApiException.class, () -> holidayService.getHoliday(String.valueOf(validYear)));
    }

    @Test
    public void testGetHoliday_ExternalApiReturnsEmptyList() {
        int validYear = 2025;
        when(botClient.getFinancialInstitutionsHoliday(validYear)).thenReturn(Collections.emptyList());
        List<FinancialInstitutionsHolidaysData> result = holidayService.getHoliday(null);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetHoliday_FutureYear() {
        int futureYear = LocalDate.now().getYear() + 5;
        Mockito.doThrow(new IllegalArgumentException("Year cannot be in the future"))
                .when(holidayValidator).validateYear(futureYear);
        assertThrows(IllegalArgumentException.class, () -> holidayService.getHoliday(String.valueOf(futureYear)));
    }
}