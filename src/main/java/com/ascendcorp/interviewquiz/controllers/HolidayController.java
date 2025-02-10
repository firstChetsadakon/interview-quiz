package com.ascendcorp.interviewquiz.controllers;

import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import com.ascendcorp.interviewquiz.services.HolidayService;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private final HolidayService holidayService;
    private final Logger logger = LoggerFactory.getLogger(HolidayController.class);

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public List<FinancialInstitutionsHolidaysData> getHoliday(
        @RequestParam(value = "year", required = false) String year
    ) {
        logger.info("Start getHoliday()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get holiday in year

        logger.info(
            "End getHoliday() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return Collections.emptyList();
    }

    @GetMapping("/nearest")
    public FinancialInstitutionsHolidaysData getNearestHoliday() {
        logger.info("Start getNearestHoliday()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get nearest holiday in future

        logger.info(
            "End getNearestHoliday() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return null;
    }

    @GetMapping("/range")
    public List<FinancialInstitutionsHolidaysData> getHolidayInRange(
        @RequestParam(value = "startDate") String startDate,
        @RequestParam(value = "endDate") String endDate
    ) {
        logger.info("Start getHolidayInRange()");
        long start = System.nanoTime();
        //TODO Implement method to call service to get holiday in range

        logger.info(
            "End getHolidayInRange() [{} ms]",
            (System.nanoTime() - start) / 1_000_000
        );
        return Collections.emptyList();
    }

}
