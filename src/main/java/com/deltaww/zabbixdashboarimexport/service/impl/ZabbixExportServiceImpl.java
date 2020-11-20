package com.deltaww.zabbixdashboarimexport.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.deltaww.zabbixdashboarimexport.config.ZabbixConfProperties;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.deltaww.zabbixdashboarimexport.service.ZabbixAuthService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixBaseService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ZabbixExportServiceImpl extends ZabbixBaseService implements ZabbixExportService {
    @Autowired
    private ZabbixConfProperties zabbixConfProperties;
    @Autowired
    private ZabbixAuthService zabbixAuthService;
    private static final String METHOD="dashboard.get";

    @Override
    public Response<ArrayList> exportDashboard(Map<String, Object> params) throws IllegalAccessException {
        ZabbixConfProperties.Source source = zabbixConfProperties.getSourceWithAuth();
        Response<String> auth = zabbixAuthService.getAuth(zabbixConfProperties.getSourceUrl(), source.getUsername(), source.getPassword());
        return this.zabbixPost(zabbixConfProperties.getSourceUrl(), METHOD, params, auth.getResult(), Response.class);
    }
}
