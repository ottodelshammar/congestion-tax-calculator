package com.example.congestiontaxcalculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "api/v1/congestiontaxcalculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping(value = "/request")
    public List<Integer> getTax(@Valid @RequestBody TaxForm taxForm) {// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date[] date
        return calculatorService.getTax(taxForm);
    }
}
