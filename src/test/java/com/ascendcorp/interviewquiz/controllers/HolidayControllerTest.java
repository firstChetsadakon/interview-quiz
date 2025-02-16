package com.ascendcorp.interviewquiz.controllers;


import com.ascendcorp.interviewquiz.services.HolidayService;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HolidayControllerTest {

    @Mock
    private HolidayService holidayService;

    @InjectMocks
    private HolidayController holidayController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(holidayController).build();
    }

    @Test
    public void testGetHoliday_ValidYear() throws Exception {
        FinancialInstitutionsHolidaysData holiday = new FinancialInstitutionsHolidaysData();
        holiday.setDate(LocalDate.of(2025, 1, 1).toString());
        holiday.setHolidayDescription("New Year's Day");

        when(holidayService.getHoliday("2025")).thenReturn(Collections.singletonList(holiday));

        mockMvc.perform(get("/holiday?year=2025")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(holiday))));
    }


    @Test
    public void testGetHoliday_NoYear() throws Exception {
        when(holidayService.getHoliday(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/holiday")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetNearestHoliday() throws Exception {
        FinancialInstitutionsHolidaysData holiday = new FinancialInstitutionsHolidaysData();
        holiday.setDate(LocalDate.now().plusDays(2).toString());
        holiday.setHolidayDescription("Nearest Holiday");

        when(holidayService.getNearestHoliday()).thenReturn(holiday);

        mockMvc.perform(get("/holiday/nearest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(holiday)));
    }

//    @Test
//    public void testGetHoliday_InvalidYear() throws Exception {
//        String invalidYear = "abcd";
//
//        when(holidayService.getHoliday(invalidYear))
//                .thenThrow(new IllegalArgumentException("For input string: \"" + invalidYear + "\""));
//
//        mockMvc.perform(get("/holiday?year=" + invalidYear)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
// test ใน integration test postman


    @Test
    public void testGetHolidayInRange() throws Exception {
        List<FinancialInstitutionsHolidaysData> holidays = Arrays.asList(
                new FinancialInstitutionsHolidaysData(
                        "Wednesday", "วันพุธ", // HolidayWeekDay, HolidayWeekDayThai
                        LocalDate.of(2025, 1, 1).toString(), "01/01/2568", // Date, DateThai
                        "New Year's Day", "วันขึ้นปีใหม่" // HolidayDescription, HolidayDescriptionThai
                ),
                new FinancialInstitutionsHolidaysData(
                        "Sunday", "วันอาทิตย์", // HolidayWeekDay, HolidayWeekDayThai
                        LocalDate.of(2025, 4, 13).toString(), "13/04/2568", // Date, DateThai
                        "Songkran Festival", "วันสงกรานต์" // HolidayDescription, HolidayDescriptionThai
                )
        );

        when(holidayService.getHolidayInRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 4, 30)))
                .thenReturn(holidays);

        mockMvc.perform(get("/holiday/range")
                        .param("startDate", "2025-01-01")
                        .param("endDate", "2025-04-30")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(holidays)));
    }

    @Test
    public void testGetHolidayInRange_NoHolidays() throws Exception {
        when(holidayService.getHolidayInRange(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30)))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/holiday/range")
                        .param("startDate", "2025-06-01")
                        .param("endDate", "2025-06-30")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

//    @Test
//    public void testGetHolidayInRange_InvalidDateRange() throws Exception {
//        String startDate = "2025-12-31";
//        String endDate = "2025-01-01";
//
//        mockMvc.perform(get("/holiday/range")
//                        .param("startDate", startDate)
//                        .param("endDate", endDate)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("""
//    {
//        "errorCode": "ERR_ILLEGAL_ARGUMENT",
//        "message": "startDate (%s) must not be after endDate (%s)",
//        "timestamp": ".*"
//    }
//    """.formatted(startDate, endDate)));
//    }
    // test ใน integration test postman
}