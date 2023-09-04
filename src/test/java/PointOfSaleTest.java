import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PointOfSaleTest {

    PointOfSale pointOfSale = new PointOfSale(); //Usually use SpringBoot and do dependency injection but keep it simple for now.
    @Test
    @DisplayName("Should create a correct rental agreement")
    void pointOfSaleCreatesCorrectRentalAgreement() throws IOException {
        RentalAgreement rentalAgreement = pointOfSale.checkout("LADW", 3, 10, "7/2/20");
        Assertions.assertEquals("LADW", rentalAgreement.getToolCode());
        Assertions.assertEquals("Ladder", rentalAgreement.getToolType());
        Assertions.assertEquals("Werner", rentalAgreement.getToolBrand());
        Assertions.assertEquals(3, rentalAgreement.getRentalDays());
        Assertions.assertEquals("7/2/20", rentalAgreement.getCheckOutDate());
        Assertions.assertEquals("7/5/20", rentalAgreement.getDueDate());
        Assertions.assertEquals(3, rentalAgreement.getChargeDays());
        Assertions.assertEquals(5.92, rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals("10%", rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(.59, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(5.33, rentalAgreement.getFinalCharge());
    }
}