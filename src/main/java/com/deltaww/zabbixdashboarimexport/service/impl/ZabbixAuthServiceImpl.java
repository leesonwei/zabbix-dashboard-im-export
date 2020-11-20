package com.deltaww.zabbixdashboarimexport.service.impl;

import com.deltaww.zabbixdashboarimexport.entity.Request;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.deltaww.zabbixdashboarimexport.service.ZabbixAuthService;
import com.deltaww.zabbixdashboarimexport.service.ZabbixBaseService;
import com.deltaww.zabbixdashboarimexport.util.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZabbixAuthServiceImpl extends ZabbixBaseService implements ZabbixAuthService {
    private static final String METHOD="user.login";
    @Override
    public Response<String> getAuth(String url, String username, String password) throws IllegalAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("user", username);
        params.put("password", password);
        Response<String> response = this.zabbixPost(url, METHOD, params, null, Response.class);
        if (!StringUtils.hasLength( response.getResult().toString() )) {
            throw new RuntimeException("auth must not be null");
        }
        return response;
    }
}
