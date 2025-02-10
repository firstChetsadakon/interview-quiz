package com.ascendcorp.interviewquiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public String getHolidayWeekDay() {
        return holidayWeekDay;
    }

    public void setHolidayWeekDay(String holidayWeekDay) {
        this.holidayWeekDay = holidayWeekDay;
    }

    public String getHolidayWeekDayThai() {
        return holidayWeekDayThai;
    }

    public void setHolidayWeekDayThai(String holidayWeekDayThai) {
        this.holidayWeekDayThai = holidayWeekDayThai;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateThai() {
        return dateThai;
    }

    public void setDateThai(String dateThai) {
        this.dateThai = dateThai;
    }

    public String getHolidayDescription() {
        return holidayDescription;
    }

    public void setHolidayDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }

    public String getHolidayDescriptionThai() {
        return holidayDescriptionThai;
    }

    public void setHolidayDescriptionThai(String holidayDescriptionThai) {
        this.holidayDescriptionThai = holidayDescriptionThai;
    }
}
