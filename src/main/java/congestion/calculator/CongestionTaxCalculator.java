package congestion.calculator;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CongestionTaxCalculator {

    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();
    private Calendar calendar = Calendar.getInstance();

    static {
        tollFreeVehicles.put("Motorbike", 0); // changed from motorcycle to motorbike
        tollFreeVehicles.put("Tractor", 1);
        tollFreeVehicles.put("Emergency", 2);
        tollFreeVehicles.put("Diplomat", 3);
        tollFreeVehicles.put("Foreign", 4);
        tollFreeVehicles.put("Military", 5);
        tollFreeVehicles.put("Buss", 6);

    }

    public int getTax(Vehicle vehicle, Date[] dates) {
        Date intervalStart = dates[0];
        int totalFee = 0;

        for (int i = 0; i < dates.length; i++) {
            Date date = dates[i];
            calendar.setTime(date);
            int nextFee = GetTollFee(date, vehicle);
            int tempFee = GetTollFee(intervalStart, vehicle);

            long diffInMillies = date.getTime() - intervalStart.getTime();
            long minutes = diffInMillies / 1000 / 60;

            if (minutes <= 60) {
                if (totalFee > 0)
                    //totalFee -= tempFee;
                    totalFee = tempFee > totalFee ? 0 : totalFee - tempFee;
                if (nextFee >= tempFee)
                    tempFee = nextFee;
                totalFee += tempFee;
            } else {
                totalFee += nextFee;
            }
        }

        if (totalFee > 60)
            totalFee = 60;
        return totalFee;
    }

    private boolean IsTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null)
            return false;
        String vehicleType = vehicle.getVehicleType();
        return tollFreeVehicles.containsKey(vehicleType);
    }

    public int GetTollFee(Date date, Vehicle vehicle) {
        if (IsTollFreeDate(date) || IsTollFreeVehicle(vehicle))
            return 0;

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour == 6 && minute >= 0 && minute <= 29)
            return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59)
            return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59)
            return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29)
            return 13;
        else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59)
            return 8;
        else if (hour == 15 && minute >= 0 && minute <= 29)
            return 13;
        else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59)
            return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59)
            return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29)
            return 8;
        else
            return 0;
    }

    private Boolean IsTollFreeDate(Date date) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month++; // add 1
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
            return true;

        if (year == 2013) {
            if ((month == 1 && dayOfMonth == 1) || (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29))
                    || (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30))
                    || (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9))
                    || (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) || (month == 7)
                    || (month == 11 && dayOfMonth == 1) || (month == 12
                    && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
                return true;
            }
        }
        return false;
    }
}
