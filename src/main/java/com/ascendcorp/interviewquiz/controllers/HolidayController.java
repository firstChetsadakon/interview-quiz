package com.ascendcorp.interviewquiz.controllers;

import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.ascendcorp.interviewquiz.services.HolidayService;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
@Slf4j
public class HolidayController {


    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public List<FinancialInstitutionsHolidaysData> getHoliday(
        @RequestParam(value = "year", required = false) String year
    ) {
        log.info("Start getHoliday()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get holiday in year
        List<FinancialInstitutionsHolidaysData> response = holidayService.getHoliday(year);
        log.info(
            "End getHoliday() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return response;
    }

    @GetMapping("/nearest")
    public FinancialInstitutionsHolidaysData getNearestHoliday() {
        log.info("Start getNearestHoliday()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get nearest holiday in future
        FinancialInstitutionsHolidaysData response = holidayService.getNearestHoliday();
        log.info(
            "End getNearestHoliday() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return response;
    }

    @GetMapping("/range")
    public List<FinancialInstitutionsHolidaysData> getHolidayInRange(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate")   @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate
    ) {
        log.info("Start getHolidayInRange()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get holiday in range
        List<FinancialInstitutionsHolidaysData> response = holidayService.getHolidayInRange(startDate, endDate);
        log.info(
            "End getHolidayInRange() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return response;
    }

}
