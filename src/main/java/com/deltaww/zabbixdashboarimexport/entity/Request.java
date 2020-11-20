package com.deltaww.zabbixdashboarimexport.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Request {
    private String jsonrpc;
    private String method;
    private Map<String, Object> params;
    private Integer id;
    private String auth;
}
