package toolObjects;

import org.apache.groovy.parser.antlr4.util.StringUtils;

import java.math.BigDecimal;

public class Tool {
    private String toolCode;
    private String toolType;
    private String brand;
    private BigDecimal dailyCharge;
    private boolean hasWeekdayCharge;
    private boolean hasWeekendCharge;
    private boolean hasHolidayCharge;

    private Tool(ToolBuilder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.brand = builder.brand;
        this.dailyCharge = builder.dailyCharge;
        this.hasWeekdayCharge = builder.hasWeekdayCharge;
        this.hasWeekendCharge = builder.hasWeekendCharge;
        this.hasHolidayCharge = builder.hasHolidayCharge;
    }


    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean hasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    public boolean hasWeekendCharge() {
        return hasWeekendCharge;
    }

    public boolean hasHolidayCharge() {
        return hasHolidayCharge;
    }

    public boolean isEmpty(){
        return StringUtils.isEmpty(toolCode);
    }

    public static class ToolBuilder{//[IfICould] - Duplicate of commentary under RentalAgreement.class builder.
        private String toolCode;
        private String toolType;
        private String brand;
        private BigDecimal dailyCharge;
        private boolean hasWeekdayCharge;
        private boolean hasWeekendCharge;
        private boolean hasHolidayCharge;

        public ToolBuilder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public ToolBuilder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public ToolBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public ToolBuilder setDailyCharge(BigDecimal dailyCharge) {
            this.dailyCharge = dailyCharge;
            return this;
        }

        public ToolBuilder setHasWeekdayCharge(boolean hasWeekdayCharge) {
            this.hasWeekdayCharge = hasWeekdayCharge;
            return this;
        }

        public ToolBuilder setHasWeekendCharge(boolean hasWeekendCharge) {
            this.hasWeekendCharge = hasWeekendCharge;
            return this;
        }

        public ToolBuilder setHasHolidayCharge(boolean hasHolidayCharge) {
            this.hasHolidayCharge = hasHolidayCharge;
            return this;
        }

        public Tool build(){
            return new Tool(this);
        }
    }
}
