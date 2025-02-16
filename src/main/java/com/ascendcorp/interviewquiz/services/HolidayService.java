package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.client.BotClient;
import com.ascendcorp.interviewquiz.exceptions.ExternalApiException;
import com.ascendcorp.interviewquiz.models.FinancialInstitutionsHolidaysData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ascendcorp.interviewquiz.validators.HolidayValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HolidayService {

    private final BotClient botClient;
    private final HolidayValidator holidayValidator;

    @Autowired
    public HolidayService(BotClient botClient, HolidayValidator holidayValidator) {
        this.botClient = botClient;
        this.holidayValidator = holidayValidator;
    }

    @Cacheable(cacheNames = "holidaysByYear",
            key = "#p0 == null ? 'CURRENT_YEAR' : #p0",
            unless = "#result == null || #result.isEmpty()")
    /* กำหนด default ของ parameter ตัวที่ 1
     กรณีที่ ค่า parameter year ของเราสามารถกรอก null เข้ามาได้
     สำหรับ การ cache ปีปัจจุบัน @First
      */
    public List<FinancialInstitutionsHolidaysData> getHoliday(String year) {
        log.info("entering getHoliday : year={}", year);
        //TODO Implement method to get holiday data in year
        // ถ้าไม่มี year หรือ year = null, กำหนดปีปัจจุบันเป็น default
        int useYear = (year != null) ? Integer.parseInt(year) : LocalDate.now().getYear();
        holidayValidator.validateYear(useYear);
        List<FinancialInstitutionsHolidaysData> response = botClient.getFinancialInstitutionsHoliday(useYear);
        if (response == null) {
            throw new ExternalApiException("No data returned from BOT API for year " + year);
        }
        return response;
    }

    public FinancialInstitutionsHolidaysData getNearestHoliday() {
        log.info("entering getNearestHoliday");
        //TODO Implement method to get nearest holiday data in future

        /* getHoliday จะไม่ใช้ ค่าใน cache จาก @Cacheable
        เพราะ self-invocation ถูก cancel ใน level ของ runtime
        ซึ่งตรงกับสิ่งที่เราต้องการพอดี เป็น alert ที่แจ้งเตือนว่า cache
         จะไม่ถูกนำมาใช้งานเฉยๆ @First
         */
        List<FinancialInstitutionsHolidaysData> thisYearHolidays = getHoliday(String.valueOf(LocalDate.now().getYear()));
        // filter เฉพาะ day >= วันนี้
        FinancialInstitutionsHolidaysData date= new FinancialInstitutionsHolidaysData();
        return thisYearHolidays.stream()
                .filter(h -> {
                    LocalDate holidayDate = LocalDate.parse(h.getDate());
                    return !holidayDate.isBefore(LocalDate.now()); // || holidayDate.isEqual(LocalDate.now())
                })
                .min(Comparator.comparing(h -> LocalDate.parse(h.getDate())))
                .orElse(null);
    }

    public List<FinancialInstitutionsHolidaysData> getHolidayInRange(LocalDate startDate, LocalDate endDate) {
        log.info("entering getHolidayInRange");
        //TODO Implement method to get holiday data in range
        holidayValidator.validateRange(startDate,endDate);
        List<FinancialInstitutionsHolidaysData> allHolidays = new ArrayList<>();
        int yearStart = startDate.getYear();
        int yearEnd = endDate.getYear();

        for (int y = yearStart; y <= yearEnd; y++) {
            /* getHoliday จะไม่ใช้ ค่าใน cache จาก @Cacheable
            เพราะ self-invocation ถูก cancel ใน level ของ runtime
            ซึ่งตรงกับสิ่งที่เราต้องการพอดี เป็น alert ที่แจ้งเตือนว่า cache
            จะไม่ถูกนำมาใช้งานเฉยๆ @First
             */
            allHolidays.addAll(getHoliday(String.valueOf(y)));
        }
        return allHolidays.stream()
                .filter(h -> {
                    LocalDate holidayDate = LocalDate.parse(h.getDate());
                    return !holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
    }
}
