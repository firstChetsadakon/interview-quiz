package com.ascendcorp.interviewquiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BotResponse {

    private FinancialInstitutionsHolidaysResponse result;

    public FinancialInstitutionsHolidaysResponse getResult() {
        return result;
    }

    public void setResult(FinancialInstitutionsHolidaysResponse result) {
        this.result = result;
    }
}
