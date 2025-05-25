package com.yins.health.util.http;

import com.alibaba.fastjson2.JSON;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.ProxySelector;
import java.util.Map;

/**
 * http操作工具类
 */
public class HttpUtils {
    private static final int MAX_TOTAL_CONNECTIONS = 100; // 最大连接数
    private static final int MAX_PER_ROUTE_CONNECTIONS = 30; // 每个路由的最大连接数
    private static final int CONNECTION_TIMEOUT = 5000; // 连接超时时间，单位为毫秒 (向池中获取请求实例的时间)
    private static final int SOCKET_TIMEOUT = 10000; // 套接字超时时间，单位为毫秒

    private static final RestTemplate restTemplate;

    static {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE_CONNECTIONS);

        // 配置连接请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        // 创建 HttpClient 实例，并设置连接池和请求配置
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault()))
                .build();

        // 使用 HttpClient 实例创建 HttpComponentsClientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // 创建 RestTemplate 实例
        restTemplate = new RestTemplate(httpRequestFactory);
    }




    /**
     * get
     * @param params 自动识别是 a=1&b=2 还是 json的格式
     */
    public static String get(String urlString, String params) {
        Map<String, Object> paramMap = toParamMap(params);
        if (paramMap == null) {
            return get(urlString + "&" + params);
        } else {
            return get(urlString, paramMap);
        }
    }

    private static Map<String, Object> toParamMap(String params) {
        try {
            return JSON.parseObject(params);
        } catch (Exception e) {
            return null;
        }
    }

    public static String get(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public static String get(String url, Map<String, Object> params) {
        return get(url, null, params);
    }

    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        MultiValueMap<String, Object> queryParams = new LinkedMultiValueMap<>();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                queryParams.add(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, queryParams);
        return response.getBody();
    }

    public static String post(String url) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        return response.getBody();
    }

    public static String post(String url, Map<String, Object> headers, Map<String, Object> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                requestBody.add(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }

    public static String post(String url, Map<String, Object> headers, String jsonBody) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }

    public static <T> ResponseEntity<String> exchange(String url, HttpMethod httpMethod, HttpEntity<T> tHttpEntity, Class<String> stringClass) {
        return restTemplate.exchange(url, httpMethod, tHttpEntity, stringClass);
    }
}
