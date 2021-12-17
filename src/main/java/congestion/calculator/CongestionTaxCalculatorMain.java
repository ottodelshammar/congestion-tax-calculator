package congestion.calculator;

import java.util.*;
import java.text.*;

public class CongestionTaxCalculatorMain {
    private static int tax;

    public static void main(String[] args) throws ParseException {

        CongestionTaxCalculator CTC = new CongestionTaxCalculator();

        String[] dateStrings = {"2013-01-14 21:00:00", "2013-01-15 21:00:00", "2013-02-07 06:23:27",
                "2013-02-07 15:27:00", "2013-02-08 06:27:00", "2013-02-08 06:20:27", "2013-02-08 14:35:00",
                "2013-02-08 15:29:00", "2013-02-08 15:47:00", "2013-02-08 16:01:00", "2013-02-08 16:48:00",
                "2013-02-08 17:49:00", "2013-02-08 18:29:00", "2013-02-08 18:35:00", "2013-03-26 14:25:00",
                "2013-03-28 14:07:27"};

        // Converting date strings to dates
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date[] dates = new Date[dateStrings.length];
        for (int i = 0; i < dateStrings.length; i++) {
            dates[i] = formatter.parse(dateStrings[i]);
        }

        tax = CTC.getTax(new Motorbike(), dates);
        System.out.println("Tax = " + tax);
        System.out.println(dates[0]);
    }
}