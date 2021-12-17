package com.example.congestiontaxcalculator;

import congestion.calculator.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CTCalculatorTest {

    CongestionTaxCalculator ctc = new CongestionTaxCalculator();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final Vehicle car = new Car();
    final Vehicle moto = new Motorbike();
    final Vehicle buss = new Buss();
    final Vehicle diplomat = new Diplomat();
    final Vehicle emergency = new Emergency();
    final Vehicle foreign = new Foreign();
    final Vehicle military = new Military();
    final Vehicle Tractor = new Tractor();

    final Date[] firstTestArray = new Date[]{formatter.parse(("2013-02-01 06:35:00"))};


    final Date[] under60MinArray = new Date[]{
            formatter.parse("2013-02-01 06:35:00"),
            formatter.parse("2013-02-01 06:50:00"),
            formatter.parse("2013-02-01 07:15:00"),
    };

    final Date[] holidayDates = new Date[]{
            formatter.parse("2013-01-01 06:35:00"),
            formatter.parse("2013-03-28 06:35:00"),
            formatter.parse("2013-04-01 06:35:00"),
            formatter.parse("2013-04-30 06:35:00"),
            formatter.parse("2013-05-01 06:35:00"),
            formatter.parse("2013-05-08 06:35:00"),
            formatter.parse("2013-05-09 06:35:00"),
            formatter.parse("2013-06-05 06:35:00"),
            formatter.parse("2013-06-06 06:35:00"),
            formatter.parse("2013-06-21 06:35:00"),
            formatter.parse("2013-07-08 06:35:00"),
            formatter.parse("2013-11-01 06:35:00"),
            formatter.parse("2013-12-24 06:35:00"),
            formatter.parse("2013-12-25 06:35:00"),
            formatter.parse("2013-12-26 06:35:00"),
            formatter.parse("2013-12-31 06:35:00")
    };

    public CTCalculatorTest() throws ParseException {
    }


    @BeforeAll
    static void setup() {
    }

    @Test
    void getRegularTax() {
        assertEquals(13, ctc.getTax(car, firstTestArray));
    }


    @Test
    void testTaxfreeVehicle() {
        assertEquals(0, ctc.getTax(moto, firstTestArray));
        assertEquals(0, ctc.getTax(buss, firstTestArray));
        assertEquals(0, ctc.getTax(diplomat, firstTestArray));
        assertEquals(0, ctc.getTax(emergency, firstTestArray));
        assertEquals(0, ctc.getTax(military, firstTestArray));
        assertEquals(0, ctc.getTax(Tractor, firstTestArray));

    }

    @Test
    void testHoliday() {
        assertEquals(0, ctc.getTax(car, holidayDates));
    }

    @Test
    void testUnder60Minutes() {
        assertEquals(18, ctc.getTax(car, under60MinArray));
    }
}
