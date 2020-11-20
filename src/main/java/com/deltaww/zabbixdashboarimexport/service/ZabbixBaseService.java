package com.deltaww.zabbixdashboarimexport.service;

import com.deltaww.zabbixdashboarimexport.config.ZabbixConfProperties;
import com.deltaww.zabbixdashboarimexport.entity.Request;
import com.deltaww.zabbixdashboarimexport.entity.Response;
import com.deltaww.zabbixdashboarimexport.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ZabbixBaseService {
    @Autowired
    private RestTemplate restTemplate;

    public <T> T zabbixPost(String url, String method, Map<String, Object> params, @Nullable String auth, Class<T> clazz) throws IllegalAccessException {
        Request request = new Request();
        request.setId(1);
        request.setJsonrpc("2.0");
        request.setMethod(method);
        request.setParams(params);
        request.setAuth(auth);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(BeanUtil.objectToMap(request));
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity , clazz);
        log.debug("request:{}, method:{}, params: {}, response: {}", url, method, params, response);
        return response.getBody();
    }

    public <T> T zabbixGet(String url, String method, Map<String, Object> params, @Nullable String auth, Class<T> clazz) throws IllegalAccessException {
        Request request = new Request();
        request.setId(1);
        request.setJsonrpc("2.0");
        request.setMethod(method);
        request.setParams(params);
        request.setAuth(auth);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(BeanUtil.objectToMap(request));
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity , clazz);
        log.debug("request:{}, method:{}, params: {}, response: {}", url, method, params, response);
        return response.getBody();
    }
}
