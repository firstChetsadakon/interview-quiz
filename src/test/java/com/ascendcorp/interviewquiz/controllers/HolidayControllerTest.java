package com.ascendcorp.interviewquiz.controllers;

import com.ascendcorp.interviewquiz.services.HolidayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class HolidayControllerTest {

    @Mock
    private HolidayService holidayService;

    private HolidayController holidayController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        holidayController = new HolidayController(holidayService);
        mockMvc = MockMvcBuilders.standaloneSetup(holidayController).build();
    }

    //TODO Implement unit test for HolidayController

}