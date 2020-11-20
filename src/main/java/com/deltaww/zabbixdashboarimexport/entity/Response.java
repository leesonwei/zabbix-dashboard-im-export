package com.deltaww.zabbixdashboarimexport.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Response<T> {
    private String jsonrpc;
    private T result;
    private Integer id;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"jsonrpc\":\"")
                .append(jsonrpc).append('\"');
        sb.append(",\"result\":")
                .append(result.toString());
        sb.append(",\"id\":")
                .append(id);
        sb.append('}');
        return sb.toString();
    }
}
