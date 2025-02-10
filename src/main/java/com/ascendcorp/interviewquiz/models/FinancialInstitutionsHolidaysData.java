package com.ascendcorp.interviewquiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinancialInstitutionsHolidaysData {

    @JsonProperty("HolidayWeekDay")
    private String holidayWeekDay;

    @JsonProperty("HolidayWeekDayThai")
    private String holidayWeekDayThai;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("DateThai")
    private String dateThai;

    @JsonProperty("HolidayDescription")
    private String holidayDescription;

    @JsonProperty("HolidayDescriptionThai")
    private String holidayDescriptionThai;

}
