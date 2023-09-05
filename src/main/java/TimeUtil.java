import toolObjects.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

    public boolean isDateAWeekend(String date){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY ||  dayOfWeek == DayOfWeek.SUNDAY;
    }

    public Integer calculateChargeDays(String checkoutDate, String dueDate, Tool tool) throws ParseException {
        List<String> daysCheckedOut = getDateRange(checkoutDate, dueDate);
        Integer countOfChargableDays = daysCheckedOut.size()-1;
        for(int i = 1; i<daysCheckedOut.size(); i++){ //Start at 1 because the list contains every date from start to end inclusive. We start charging day after first day.
            String day = daysCheckedOut.get(i);
            if(isDateAWeekend(day) && !tool.hasWeekendCharge()){
                countOfChargableDays--;
            }if(isDateAHoliday(day) && !tool.hasHolidayCharge()){
                countOfChargableDays--;
            }if(!isDateAWeekend(day) && !tool.hasWeekdayCharge()){
                countOfChargableDays--;
            }
        }
        return countOfChargableDays;
    }

    public String formatDate(String inputDate) throws ParseException {
        String regex = "^(0?[1-9]|1[0-2])/\\d{1,2}/\\d{2}$"; //If date comes in as M/DD/YY then change it to MM/dd/yy format.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputDate);
        if (matcher.matches()) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("M/dd/yy");
            // Parse the input date string
            Date date = inputFormat.parse(inputDate);
            // Create a SimpleDateFormat for the desired output format with leading zero
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yy");
            return outputFormat.format(date);
        }
        return inputDate;
    }

    public List<String> getDateRange(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date parsedStartDate = sdf.parse(startDate);
        Date parsedEndDate = sdf.parse(endDate);
        List<String> dateRange = new ArrayList<>();

        long diffInMillies = Math.abs(parsedEndDate.getTime() - parsedStartDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Date currentDate = parsedStartDate;
        for (int i = 0; i<=diffInDays; i++) {
            dateRange.add(sdf.format(currentDate));
            currentDate = new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(1));
        }

        return dateRange;
    }

    public String calculateDueDate(String date, int rentalDateCount){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        ZonedDateTime futureDate = zonedDateTime.plusDays(rentalDateCount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return futureDate.format(formatter);
    }

    private boolean isDateAHoliday(String date){
        return isIndependenceDay(date) || isLaborDay(date);
    }

    private boolean isIndependenceDay(String date){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        int year = zonedDateTime.getYear();
        ZonedDateTime independenceDay = ZonedDateTime.of(year, Month.JULY.getValue(),
                4,
                0,
                0,
                0,
                0,
                zonedDateTime.getZone());

        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        // Adjust for observed rules if Independence Day falls on a weekend
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return zonedDateTime.toLocalDate().isEqual(independenceDay.toLocalDate());
    }

    private boolean isLaborDay(String date){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        int year = zonedDateTime.getYear();
        ZonedDateTime laborDay = ZonedDateTime.of(year,
                Month.SEPTEMBER.getValue(),
                1,
                0,
                0,
                0,
                0,
                zonedDateTime.getZone());

        // Find the first Monday in September
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }
        return zonedDateTime.toLocalDate().isEqual(laborDay.toLocalDate());
    }

    private ZonedDateTime convertDateStringToObject(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        ZonedDateTime result = localDate.atStartOfDay(ZoneId.systemDefault());
        return result;
    }
}
