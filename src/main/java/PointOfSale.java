import toolObjects.JsonToolRepository;
import toolObjects.Tool;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

/**
 * Returns a properly formatted RentalAgreement object for printing to the console.
 */
public class PointOfSale {
    private TimeUtil timeUtil;
    private JsonToolRepository jsonToolRepository;
    public PointOfSale() {
        try{
            this.timeUtil = new TimeUtil();
            this.jsonToolRepository = new JsonToolRepository();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param toolCode - String indicating a unique ID for every tool.
     * @param rentalDayCount - Simple int number of days a potential rental will last.
     * @param discountPercent - int number that represents a percentage discount represented as a whole number. IE 5 = 5%.
     * @param checkoutDate - A string representing a checkout date. Will be formatted internally to a java date time object.
     * @return RentalAgreement - Represents an object that has all the data pertaining to a rental agreement.
     */
    public RentalAgreement checkout(String toolCode, String checkoutDate, int rentalDayCount, int discountPercent) {
        if(rentalDayCount<1){
            throw new IllegalArgumentException("Number of days for the rental must be at least 1.");
        }if(discountPercent<0 || discountPercent>100){
            throw new IllegalArgumentException("Discount percentage must be within the range of 1-100.");
        }
        Tool tool = jsonToolRepository.getTool(toolCode);
        if(tool.isEmpty()){ //[IfICould] - I might consider just throwing an empty rental agreement object from an API perspective. Thought it would be better to demo some error handling.
            throw new IllegalArgumentException("Invalid tool code. Verify ALL CAPS and has valid entry in repo. Tool code: " + toolCode);
        }

        String formattedCheckoutDate = "";
        String dueDate = "";
        Integer chargeDays = 0;
        try{
            formattedCheckoutDate = timeUtil.formatDate(checkoutDate); //[IfICould] - If I couldn't get time as a ISO date string or time stamp on the API side i'd enforce a contract that rejects dates not in accepted formats.
            dueDate = timeUtil.calculateDueDate(formattedCheckoutDate, rentalDayCount);
            chargeDays = timeUtil.calculateChargeDays(formattedCheckoutDate, dueDate, tool);
        }catch (ParseException e){ //Error out completely as we cannot fulfill the contract anymore.
            throw new RuntimeException(e);
        }
        BigDecimal preDiscountCharge = calculatePreDiscountCharge(chargeDays, tool.getDailyCharge());
        BigDecimal discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent);
        BigDecimal finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

        RentalAgreement result = new RentalAgreement
                .RentalAgreementBuilder()
                .setToolCode(toolCode)
                .setToolType(tool.getToolType())
                .setToolBrand(tool.getBrand())
                .setRentalDays(String.valueOf(rentalDayCount))
                .setCheckOutDate(formattedCheckoutDate)
                .setDueDate(timeUtil.calculateDueDate(formattedCheckoutDate, rentalDayCount))
                .setDailyRentalCharge(String.valueOf(tool.getDailyCharge()))
                .setChargeDays(String.valueOf(chargeDays))
                .setPreDiscountCharge(String.valueOf(preDiscountCharge))
                .setDiscountPercent(discountPercent+"%")
                .setDiscountAmount(String.valueOf(discountAmount))
                .setFinalCharge(String.valueOf(finalCharge))
                .build();
        return result;
    }

    private BigDecimal calculateFinalCharge(BigDecimal preDiscountAmount, BigDecimal discountAmount){
        return preDiscountAmount.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal calculatePreDiscountCharge(int numberOfDays, BigDecimal dailyCharge){
        BigDecimal total = BigDecimal.valueOf(numberOfDays).multiply(dailyCharge);
        total = total.setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    private BigDecimal calculateDiscountAmount(BigDecimal charge, int percentOff){
        BigDecimal discountPercent = BigDecimal.valueOf(percentOff).divide(BigDecimal.valueOf(100));
        BigDecimal discountAmount = charge.multiply(discountPercent);
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        return discountAmount;
    }

}
