package com.example.congestiontaxcalculator.calculator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class TaxForm {

    @NotEmpty(message = "Dates are required")
    @NotNull(message = "Dates are required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date[] dates;

    @Size(max = 10, message = "Vehicle description is limited to 10 characters")
    private String vehicle;

    public Date[] getDates() {
        return dates;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
