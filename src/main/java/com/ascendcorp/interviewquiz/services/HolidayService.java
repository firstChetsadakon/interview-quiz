package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {

    private final Logger logger = LoggerFactory.getLogger(HolidayService.class);


    public List<FinancialInstitutionsHolidaysData> getHoliday() {
        logger.info("entering getHoliday : {}");
        //TODO Implement method to get holiday data in year

        return Collections.emptyList();
    }

    public FinancialInstitutionsHolidaysData getNearestHoliday() {
        logger.info("entering getNearestHoliday");
        //TODO Implement method to get nearest holiday data in future

        return null;
    }

    public List<FinancialInstitutionsHolidaysData> getHolidayInRange() {
        logger.info("entering getHolidayInRange");
        //TODO Implement method to get holiday data in range

        return Collections.emptyList();
    }
}
