package com.deltaww.zabbixdashboarimexport.service;

import com.alibaba.fastjson.JSONArray;
import com.deltaww.zabbixdashboarimexport.entity.Response;

import java.util.ArrayList;
import java.util.Map;

public interface ZabbixExportService {
    Response<ArrayList> exportDashboard(Map<String, Object> params) throws IllegalAccessException;
}
