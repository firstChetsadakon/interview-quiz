package com.ascendcorp.interviewquiz.models;

import lombok.Data;
import java.util.List;

@Data
public class FinancialInstitutionsHolidaysResponse {

    private String api;

    private String timestamp;

    private List<FinancialInstitutionsHolidaysData> data;

}
