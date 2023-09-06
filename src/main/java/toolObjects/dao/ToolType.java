package toolObjects.dao;

/**
 * DAO that data from ToolData.json maps to.
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
