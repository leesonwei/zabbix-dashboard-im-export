package com.deltaww.zabbixdashboarimexport.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.deltaww.zabbixdashboarimexport.config.ZabbixConfProperties;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.deltaww.zabbixdashboarimexport.service.ZabbixAuthService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixBaseService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZabbixImportServiceImpl extends ZabbixBaseService implements ZabbixImportService {
    @Autowired
    private ZabbixConfProperties zabbixConfProperties;
    @Autowired
    private ZabbixAuthService zabbixAuthService;
    private static final String METHOD="dashboard.create";

    @Override
    public Response<ArrayList> importDashboard(Map<String, Object> params) throws IllegalAccessException {
        ZabbixConfProperties.Source source = zabbixConfProperties.getSourceWithAuth();
        Response<String> auth = zabbixAuthService.getAuth(zabbixConfProperties.getSourceUrl(), source.getUsername(), source.getPassword());
        return this.zabbixPost(zabbixConfProperties.getTargetUrl(), METHOD, params, auth.getResult(), Response.class);
    }
}
