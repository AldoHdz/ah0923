package tools.interfaces;

import tools.Tool;
import tools.dao.ToolType;

import java.io.IOException;

public interface ToolRepository {
    //[IfICould] - Created this simple interface just to indicate our data can come from anywhere. JSON was simplest here due to size but it could be a SQL DB NoSQL DB, etc.
    Tool getTool(String toolType);
}
