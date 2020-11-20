package com.deltaww.zabbixdashboarimexport.commands;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.deltaww.zabbixdashboarimexport.config.ZabbixConfProperties;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.deltaww.zabbixdashboarimexport.service.ZabbixExportService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixImportService;
import com.deltaww.zabbixdashboarimexport.util.DashboardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 执行导入导出
 */
@Slf4j
@Component
public class ZabbixCommand implements CommandLineRunner {
    @Autowired
    private ZabbixImportService zabbixImportService;
    @Autowired
    private ZabbixExportService zabbixExportService;
    @Autowired
    private ZabbixConfProperties zabbixConfProperties;


    @Override
    public void run(String... args) throws Exception {
        //exportDashboard()
        Map<String, Object> exportConfig = new HashMap<>();
        exportConfig.put("output", "extend");
        exportConfig.put("selectWidgets", "extend");
        exportConfig.put("selectUsers", "extend");
        exportConfig.put("selectUserGroups", "extend");
        Map<String, Object> search = new HashMap<>();
        search.put("name", zabbixConfProperties.getDashboardName());
        exportConfig.put("search", search);
        Response<ArrayList> exportDashboard = zabbixExportService.exportDashboard(exportConfig);
        //importDashboard
        String dashboardName = zabbixConfProperties.getDashboardName();
        if (zabbixConfProperties.getSourceUrl().equals(zabbixConfProperties.getTargetUrl())) {
            dashboardName += " new";
        }
        String createParams = DashboardUtil.getCreateParams(exportDashboard.getResult(), dashboardName);
        Map<String, Object> map = JSON.parseObject(createParams, new TypeReference<Map<String, Object>>() {});
        Response<ArrayList> importDashboard = zabbixImportService.importDashboard(map);
        log.info("import successed, new dashboard: {}", importDashboard.getResult());
    }
}
