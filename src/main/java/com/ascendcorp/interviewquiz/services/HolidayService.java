package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.client.BotClient;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HolidayService {

    private final BotClient botClient;

    @Autowired
    public HolidayService(BotClient botClient) {
        this.botClient = botClient;
    }


    public List<FinancialInstitutionsHolidaysData> getHoliday(Integer year) {
        log.info("entering getHoliday : year={}", year);
        //TODO Implement method to get holiday data in year
        // ถ้าไม่มี year หรือ year = null, กำหนดปีปัจจุบันเป็น default
        int useYear = (year != null) ? year : LocalDate.now().getYear();

        return botClient.getFinancialInstitutionsHoliday(useYear);
    }

    public FinancialInstitutionsHolidaysData getNearestHoliday() {
        log.info("entering getNearestHoliday");
        //TODO Implement method to get nearest holiday data in future

        return null;
    }

    public List<FinancialInstitutionsHolidaysData> getHolidayInRange() {
        log.info("entering getHolidayInRange");
        //TODO Implement method to get holiday data in range

        return Collections.emptyList();
    }
}
