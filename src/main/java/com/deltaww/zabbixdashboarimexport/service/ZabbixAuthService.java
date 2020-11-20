package com.deltaww.zabbixdashboarimexport.service;

import com.deltaww.zabbixdashboarimexport.entity.Response;

public interface ZabbixAuthService {
    Response<String> getAuth(String url, String username, String password) throws IllegalAccessException;
}
