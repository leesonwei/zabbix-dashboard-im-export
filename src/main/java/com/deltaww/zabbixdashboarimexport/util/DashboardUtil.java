package com.deltaww.zabbixdashboarimexport.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class DashboardUtil {
    public static String getCreateParams(ObjectMapper objectMapper, Response responseResult, String newName) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(responseResult.toString());
        for (JsonNode node : jsonNode) {
            //todo set dashboard name;
            if (node instanceof ObjectNode) {
                ObjectNode objectNode = (ObjectNode) jsonNode;
                if (objectNode.has("dashboardid")) {
                    objectNode.remove("dashboardid");
                }
                if (objectNode.has("widgets")) {
                    JsonNode widgets = objectNode.get("widgets");
                    for (JsonNode widget : widgets) {
                        if (widget.has("widgetid")) {
                            ObjectNode widgetNode = (ObjectNode) widget;
                            widgetNode.remove("widgetid");
                        }
                    }
                }
            }
        }

        return  objectMapper.writeValueAsString(jsonNode);
    }

    public static String getCreateParams(ArrayList<Object> responseResult, String newName) throws JsonProcessingException {
        //todo define a Object map to result
        Object obj = responseResult.get(0);
        JSONObject json = null;
        if (obj instanceof JSONObject) {
            json = (JSONObject) obj;
        }
        json.put("name", newName);
        json.remove("dashboardid");
        JSONArray widgets = json.getJSONArray("widgets");
        for (Object object : widgets) {
            JSONObject widget = (JSONObject) object;
            widget.remove("widgetid");
        }
        return json.toJSONString();
    }
}
