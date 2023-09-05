
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private String rentalDays;
    private String checkOutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private String chargeDays;
    private String preDiscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    private RentalAgreement(RentalAgreement.RentalAgreementBuilder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.toolBrand = builder.toolBrand;
        this.rentalDays = builder.rentalDays;
        this.checkOutDate = builder.checkOutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeDays = builder.chargeDays;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public String getRentalDays() {
        return rentalDays;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public String getChargeDays() {
        return chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getFinalCharge() {
        return finalCharge;
    }

    @Override
    public String toString(){
        return "Tool code: " + toolCode +
                '\n' +
                "Tool type: " + toolType +
                '\n' +
                "Tool Brand: " + toolBrand +
                '\n' +
                "Rental Days: " + rentalDays +
                '\n' +
                "Check out date: " + checkOutDate +
                '\n' +
                "Due date: " + dueDate +
                '\n' +
                "Daily rental charge: " + dailyRentalCharge +
                '\n' +
                "Charge days: " + chargeDays +
                '\n' +
                "Pre-discount charge: " + preDiscountCharge +
                '\n' +
                "Discount percent: " + discountPercent +
                '\n' +
                "Discount amount: " + discountAmount +
                '\n' +
                "Final charge: " + finalCharge;
    }

    public static class RentalAgreementBuilder{ //[IfICould] - While useful, build patterns add a lot of boilerplate. Project Lombok does this implicitly with @builder but requires too much setup for a demo.
        private String toolCode;
        private String toolType;
        private String toolBrand;
        private String rentalDays;
        private String checkOutDate;
        private String dueDate;
        private String dailyRentalCharge;
        private String chargeDays;
        private String preDiscountCharge;
        private String discountPercent;
        private String discountAmount;
        private String finalCharge;

        public RentalAgreementBuilder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public RentalAgreementBuilder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public RentalAgreementBuilder setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public RentalAgreementBuilder setRentalDays(String rentalDays) {
            this.rentalDays = rentalDays;
            return this;
        }

        public RentalAgreementBuilder setCheckOutDate(String checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public RentalAgreementBuilder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public RentalAgreementBuilder setDailyRentalCharge(String dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public RentalAgreementBuilder setChargeDays(String chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public RentalAgreementBuilder setPreDiscountCharge(String preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public RentalAgreementBuilder setDiscountPercent(String discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public RentalAgreementBuilder setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public RentalAgreementBuilder setFinalCharge(String finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }
        public RentalAgreement build(){
            return new RentalAgreement(this);
        }
    }
}
