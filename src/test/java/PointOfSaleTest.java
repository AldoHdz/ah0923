import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PointOfSaleTest {

    private static PointOfSale pointOfSale; //Usually use SpringBoot and do dependency injection but keep it simple for now.

    @BeforeAll
    public static void setUp(){
        pointOfSale = new PointOfSale();
    }

    @Test
    @DisplayName("PointOfSale should throw invalid argument exception on invalid percent discount amount - Test 1")
    void pointOfSaleThrowsCorrectErrorOnBadPercentDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("JAKR", "7/2/20", 1, 110);
        });

        String expectedMessage = "Discount percentage must be within the range of 1-100.";
        Assertions.assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    @DisplayName("Should create a correct rental agreement on a rental three day rental period including the Independence Day observance. - Test 2")
    void pointOfSaleCreatesCorrectRentalAgreementOnThreeDayAgreementWithFourthOfJuly(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("LADW","7/2/20", 3, 10 );
        Assertions.assertEquals("LADW", rentalAgreement.getToolCode());
        Assertions.assertEquals("Ladder", rentalAgreement.getToolType());
        Assertions.assertEquals("Werner", rentalAgreement.getToolBrand());
        Assertions.assertEquals("3", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/20", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("07/05/20", rentalAgreement.getDueDate());
        Assertions.assertEquals("2", rentalAgreement.getChargeDays());
        Assertions.assertEquals("3.98", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("10%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("0.40", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("3.58", rentalAgreement.getFinalCharge());
    }

    @Test
    @DisplayName("Should create a correct rental agreement on a five day rental period which includes Independence Day observance. - Test 3")
    void pointOfSaleCreatesCorrectRentalAgreementOnFiveDayAgreementWithFourthOfJuly(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("CHNS","7/2/15", 5, 25 );
        Assertions.assertEquals("CHNS", rentalAgreement.getToolCode());
        Assertions.assertEquals("Chainsaw", rentalAgreement.getToolType());
        Assertions.assertEquals("Stihl", rentalAgreement.getToolBrand());
        Assertions.assertEquals("5", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/15", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("07/07/15", rentalAgreement.getDueDate());
        Assertions.assertEquals("3", rentalAgreement.getChargeDays());
        Assertions.assertEquals("4.47", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("25%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("1.12", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("3.35", rentalAgreement.getFinalCharge());
    }

    @Test
    @DisplayName("Should create a correct rental agreement on a six day rental period which includes Labor Day observance. - Test 4")
    void pointOfSaleCreatesCorrectRentalAgreementOnFiveDayAgreementWithLaborDay(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("JAKD","9/3/15", 6, 0 );
        Assertions.assertEquals("JAKD", rentalAgreement.getToolCode());
        Assertions.assertEquals("Jackhammer", rentalAgreement.getToolType());
        Assertions.assertEquals("DeWalt", rentalAgreement.getToolBrand());
        Assertions.assertEquals("6", rentalAgreement.getRentalDays());
        Assertions.assertEquals("09/03/15", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("09/09/15", rentalAgreement.getDueDate());
        Assertions.assertEquals("3", rentalAgreement.getChargeDays());
        Assertions.assertEquals("8.97", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("0.00", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("8.97", rentalAgreement.getFinalCharge());
    }

    @Test
    @DisplayName("Should create a correct rental agreement on a nine day rental period which includes Independence Day observance. - Test 5")
    void pointOfSaleCreatesCorrectRentalAgreementOnNineDayAgreementWithLaborDay(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("JAKR","7/2/15", 9, 0 );
        Assertions.assertEquals("JAKR", rentalAgreement.getToolCode());
        Assertions.assertEquals("Jackhammer", rentalAgreement.getToolType());
        Assertions.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assertions.assertEquals("9", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/15", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("07/11/15", rentalAgreement.getDueDate());
        Assertions.assertEquals("5", rentalAgreement.getChargeDays());
        Assertions.assertEquals("14.95", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("0.00", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("14.95", rentalAgreement.getFinalCharge());
    }

    @Test
    @DisplayName("Should create a correct rental agreement on a four day rental period which includes Independence Day observance. - Test 6")
    void pointOfSaleCreatesCorrectRentalAgreementOnFourDayAgreementWithLaborDay(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("JAKR","7/2/20", 4, 50 );
        Assertions.assertEquals("JAKR", rentalAgreement.getToolCode());
        Assertions.assertEquals("Jackhammer", rentalAgreement.getToolType());
        Assertions.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assertions.assertEquals("4", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/20", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("07/06/20", rentalAgreement.getDueDate());
        Assertions.assertEquals("1", rentalAgreement.getChargeDays());
        Assertions.assertEquals("2.99", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("50%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("1.50", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("1.49", rentalAgreement.getFinalCharge());
    }

    @Test
    @DisplayName("Charges nothing on a rental period that's nothing but weekend and holiday on a tool that does not charge on either day.")
    void pointOfSaleCreatesValidContractOnRentalPeriodThatIsNothingButWeekendAndHolidayOnToolThatDoesNotChargeForThoseDays(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("JAKR","7/2/20", 3, 0 );
        Assertions.assertEquals("JAKR", rentalAgreement.getToolCode());
        Assertions.assertEquals("Jackhammer", rentalAgreement.getToolType());
        Assertions.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assertions.assertEquals("3", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/20", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("07/05/20", rentalAgreement.getDueDate());
        Assertions.assertEquals("0", rentalAgreement.getChargeDays());
        Assertions.assertEquals("0.00", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("0.00", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("0.00", rentalAgreement.getFinalCharge());
    }


    @Test
    @DisplayName("PointOfSale should throw invalid argument exception on invalid rental day counts")
    void pointOfSaleThrowsCorrectErrorOnBadRentalDayCount(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("JAKR", "7/2/20", -1, 10);
        });

        String expectedMessage = "Number of days for the rental must be at least 1.";
        Assertions.assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    @DisplayName("PointOfSale should throw invalid argument exception on invalid tool code")
    void pointOfSaleThrowsErrorOnInvalidToolCode() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("NBA", "7/2/20", 100, 10);
        });

        String expectedMessage = "Invalid tool code. Verify ALL CAPS and has valid entry in repo. Tool code: NBA";
        Assertions.assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    @DisplayName("Point of sale creates valid rental agreement for item that doesn't charge on weekdays")
    void pointOfSaleCreatesValidRentalAgreementForItemThatDoesNotChargeOnWeekdays(){
        RentalAgreement rentalAgreement = pointOfSale.checkout("NAIL","7/2/20", 40, 0 );
        Assertions.assertEquals("NAIL", rentalAgreement.getToolCode());
        Assertions.assertEquals("Nail Gun", rentalAgreement.getToolType());
        Assertions.assertEquals("Daifeng Core Industries", rentalAgreement.getToolBrand());
        Assertions.assertEquals("40", rentalAgreement.getRentalDays());
        Assertions.assertEquals("07/02/20", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("08/11/20", rentalAgreement.getDueDate());
        Assertions.assertEquals("12", rentalAgreement.getChargeDays());
        Assertions.assertEquals("38.76", rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals("0.00", rentalAgreement.getDiscountAmount());
        Assertions.assertEquals("38.76", rentalAgreement.getFinalCharge());
    }

}