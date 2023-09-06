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

    /**
     *
     * @param checkoutDate - String date formatted as MM/dd/yy. It's the start date provided by the user.
     * @param dueDate - String date formatted as MM/dd/yy. Calculated as checkoutDate + checkout days
     * @param tool - Tool object containing relevant tool data. Tool type, daily charge rate, if holiday or weeked charges exist etc.
     * @return - Final result calculates how many days are charged. Date is potentially every day after checkoutDate and up to dueDate minus
     * holidays and weekends if the tool doesn't charge on those days.
     * @throws ParseException - Thrown by internal function call getDateRange. Because it parses a string date and creates a list of all the dates between startDate and dueDate.
     */
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

    /**
     *
     * @param inputDate - raw date provided by user. Could be in MM/dd/yy format or M/d/yy format. We try to sanitize and convert to MM/dd/yy.
     * @return - Returns a date in MM/dd/yy format.
     * @throws ParseException   - Due to having to parse the inputDate string.
     */
    public String formatDate(String inputDate) throws ParseException {
            SimpleDateFormat inputFormat = new SimpleDateFormat("M/dd/yy");
            // Parse the input date string
            Date date = inputFormat.parse(inputDate);
            // Create a SimpleDateFormat for the desired output format with leading zero
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yy");
            return outputFormat.format(date);
    }

    /**
     * @param startDate - user specified start date for rental
     * @param endDate - Calculated end date. Is startDate + number of rental days.
     * @return - List<String> that contains every date between those two dates.
     * @throws ParseException - Because we must parse string dates with SimpleDateFormat
     */
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

    /**
     *
     * @param date - user supplied rental date start
     * @param rentalDateCount - number of days the user wants the tool
     * @return - Calculates the number of days that is. Is considered date+rentalDateCount
     */
    public String calculateDueDate(String date, int rentalDateCount){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        ZonedDateTime futureDate = zonedDateTime.plusDays(rentalDateCount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return futureDate.format(formatter);
    }

    private boolean isDateAHoliday(String date){
        return isIndependenceDay(date) || isLaborDay(date);
    }

    private boolean isDateAWeekend(String date){
        ZonedDateTime zonedDateTime = convertDateStringToObject(date);
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY ||  dayOfWeek == DayOfWeek.SUNDAY;
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
