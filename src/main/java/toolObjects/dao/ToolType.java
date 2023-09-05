package toolObjects.dao;

/**
 * Loads tool data into memory. Requires the ToolData.json file and PriceData.json to properly init.
 */
public class ToolType {
    private String toolCode;
    private String toolType;
    private String brand;

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}
