package tools.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ToolPrice {

    private String toolType;
    private BigDecimal dailyCharge;
    @JsonProperty("weekdayCharge")
    private Boolean hasWeekdayCharge;
    @JsonProperty("weekendCharge")
    private Boolean hasWeekendCharge;
    @JsonProperty("holidayCharge")
    private Boolean hasHolidayCharge;

    public String getToolType() {
        return toolType;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public Boolean hasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    public void setHasWeekdayCharge(Boolean hasWeekdayCharge) {
        this.hasWeekdayCharge = hasWeekdayCharge;
    }

    public Boolean hasWeekendCharge() {
        return hasWeekendCharge;
    }

    public Boolean hasHolidayCharge() {
        return hasHolidayCharge;
    }

    public void setHasHolidayCharge(Boolean hasHolidayCharge) {
        this.hasHolidayCharge = hasHolidayCharge;
    }
}
