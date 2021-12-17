package com.example.congestiontaxcalculator.calculator;

import congestion.calculator.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class CalculatorService {

    public List<Integer> getTax(@Valid TaxForm taxForm) {
        return List.of(
                new CongestionTaxCalculator().getTax(getVehicle(taxForm), taxForm.getDates())
        );
    }

    public Vehicle getVehicle(TaxForm taxForm) {

        if (taxForm.getVehicle().trim().equalsIgnoreCase("car")) {
            return new Car();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("diplomat")) {
            return new Diplomat();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("emergency")) {
            return new Emergency();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("foreign")) {
            return new Foreign();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("military")) {
            return new Military();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("motorbike")) {
            return new Motorbike();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("tractor")) {
            return new Tractor();
        }
        if (taxForm.getVehicle().trim().equalsIgnoreCase("buss")) {
            return new Tractor();
        }

        return null;
    }

    ;
}
