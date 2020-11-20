package com.deltaww.zabbixdashboarimexport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.xml.transform.Source;

@Configuration
@ConfigurationProperties(prefix = "zabbix")
@Data
public class ZabbixConfProperties {
    private Source source;
    private Source target;
    private String username;
    private String password;
    private String dashboardName;
    private static final String HTTP = "http://";
    private static final String URI = "/zabbix/api_jsonrpc.php";

    @Data
    public static class Source{
        private String username;
        private String password;
        private String host;
        private String port;
    }
    public String getSourceUrl(){
        return  String.format("%s%s:%s%s", HTTP , this.source.host, this.source.port, URI);
    }
    public String getTargetUrl(){
        return String.format("%s%s:%s%s", HTTP , this.target.host, this.target.port, URI);
    }
    public Source getSourceWithAuth(){
        if (!StringUtils.hasLength(source.username) && !StringUtils.hasLength(username)) {
            throw new RuntimeException("username must be set.");
        }
        if (!StringUtils.hasLength(source.password) && !StringUtils.hasLength(password)) {
            throw new RuntimeException("username must be set.");
        }
        if (!StringUtils.hasLength(source.username)) {
            source.setUsername(username);
        }
        if (!StringUtils.hasLength(source.password)) {
            source.setPassword(password);
        }
        return source;
    }
    public Source getTargetWithAuth(){
        if (!StringUtils.hasLength(target.username) && !StringUtils.hasLength(username)) {
            throw new RuntimeException("username must be set.");
        }
        if (!StringUtils.hasLength(target.password) && !StringUtils.hasLength(password)) {
            throw new RuntimeException("username must be set.");
        }
        if (!StringUtils.hasLength(target.username)) {
            target.setUsername(username);
        }
        if (!StringUtils.hasLength(target.password)) {
            target.setPassword(password);
        }
        return target;
    }
}
