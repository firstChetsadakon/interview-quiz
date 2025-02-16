package com.ascendcorp.interviewquiz.validators;


import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HolidayValidator {

    public void validateYear(Integer year) {
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative: " + year);
        }
        int currentYear = LocalDate.now().getYear();
        if (year > currentYear) {
            throw new IllegalArgumentException("Year cannot be in the future: " + year);
        }
    }


    public void validateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "startDate (" + startDate + ") must not be after endDate (" + endDate + ")"
            );
        }
    }
}

