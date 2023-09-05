package tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import tools.dao.ToolPrice;
import tools.dao.ToolType;
import tools.interfaces.ToolRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonToolRepository implements ToolRepository {
    private ToolType[] toolTypeList;
    private ToolPrice[] toolPriceList;
    private Map<String, Tool> toolMap;
    private Map<String, ToolType> toolTypeMap;
    private Map<String, ToolPrice> toolPriceMap;

    public JsonToolRepository() throws IOException {
        toolMap = new HashMap<>();
        toolTypeMap = new HashMap<>();
        toolPriceMap = new HashMap<>();
        buildToolMap();
    }

    public void buildToolMap() throws IOException {
        buildToolTypeMap();
        buildToolPriceMap();

        for(String toolCode : toolTypeMap.keySet()){
            ToolType toolType = toolTypeMap.get(toolCode);
            ToolPrice toolPrice = toolPriceMap.get(toolType.getToolType());
            toolMap.put(toolCode, new Tool.ToolBuilder().setToolCode(toolCode)
                    .setToolType(toolType.getToolType())
                    .setBrand(toolType.getBrand())
                    .setDailyCharge(toolPrice.getDailyCharge())
                    .setHasHolidayCharge(toolPrice.hasHolidayCharge())
                    .setHasWeekendCharge(toolPrice.hasWeekendCharge())
                    .setHasWeekdayCharge(toolPrice.hasWeekdayCharge())
                    .build());
        }
    }

    private void buildToolTypeMap() throws IOException {
        buildToolTypeList();
        for(ToolType toolType : toolTypeList){
            toolTypeMap.put(toolType.getToolCode(), toolType);
        }
    }

    private void buildToolPriceMap() throws IOException {
        buildToolPriceList();
        for(ToolPrice toolPrice : toolPriceList){
            toolPriceMap.put(toolPrice.getToolType(), toolPrice);
        }
    }

    private void buildToolTypeList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/configs/ToolData.json");
        toolTypeList = objectMapper.readValue(file, ToolType[].class);
    }

    private void buildToolPriceList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/configs/PriceData.json");
        toolPriceList = objectMapper.readValue(file, ToolPrice[].class);
    }

    @Override
    public Tool getTool(String toolCode) {
        return  toolMap.getOrDefault(toolCode, new Tool.ToolBuilder().build()); //Saves us from error checking. If we don't have the tool we return an empty object.

    }
}
