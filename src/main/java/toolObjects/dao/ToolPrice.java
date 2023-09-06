package toolObjects.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * DAO that data from PriceData.json maps to.
 */
public class ToolPrice {

    private String toolType;
    private BigDecimal dailyCharge;
    @JsonProperty("weekdayCharge")
    private Boolean weekdayCharge;
    @JsonProperty("weekendCharge")
    private Boolean weekendCharge;
    @JsonProperty("holidayCharge")
    private Boolean holidayCharge;

    public String getToolType() {
        return toolType;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public Boolean hasWeekdayCharge() {
        return weekdayCharge;
    }

    public Boolean hasWeekendCharge() {
        return weekendCharge;
    }

    public Boolean hasHolidayCharge() {
        return holidayCharge;
    }

}
