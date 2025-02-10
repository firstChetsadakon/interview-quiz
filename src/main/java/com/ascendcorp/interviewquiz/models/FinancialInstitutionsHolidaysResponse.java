package com.ascendcorp.interviewquiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FinancialInstitutionsHolidaysResponse {

    private String api;

    private String timestamp;

    private List<FinancialInstitutionsHolidaysData> data;


    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<FinancialInstitutionsHolidaysData> getData() {
        return data;
    }

    public void setData(
        List<FinancialInstitutionsHolidaysData> data) {
        this.data = data;
    }
}
