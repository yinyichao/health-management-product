package com.yins.health.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

@Slf4j
public class HttpUtils {

    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 30000;
    private final static int HTTP_SUCCESS = 200;

    public static ResponseBody sendPost(String url, Map<String, String> requestHead, Map<String, String> requestBody, Map<String, byte[]> requestData) {

        log.info("发送post请求url:{},参数params:{}", url, requestBody);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            String boundaryString = getBoundary();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(CONNECT_TIME_OUT);
            connection.setReadTimeout(READ_OUT_TIME);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            if (requestHead != null && requestHead.size() > 0) {
                for (String headKey : requestHead.keySet()) {
                    connection.setRequestProperty(headKey, requestHead.get(headKey));
                }
            }

            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            if (requestBody != null && requestBody.size() > 0) {
                for (String bodyKey : requestBody.keySet()) {
                    dos.writeBytes("--" + boundaryString + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"" + bodyKey + "\"\r\n");
                    dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + "\r\n");
                    dos.writeBytes("\r\n");
                    dos.write(requestBody.get(bodyKey).getBytes(StandardCharsets.UTF_8));
                    dos.writeBytes("\r\n");
                }
            }
            if (requestData != null && requestData.size() > 0) {
                for (String dataKey : requestData.keySet()) {
                    dos.writeBytes("--" + boundaryString + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"" + dataKey + "\"; filename=\"" + encode() + "\"\r\n");
                    dos.writeBytes("\r\n");
                    dos.write(requestData.get(dataKey));
                    dos.writeBytes("\r\n");
                }
            }
            dos.writeBytes("--" + boundaryString + "--" + "\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
            dos.close();
            InputStream ins;
            int code = connection.getResponseCode();
            if (code == HTTP_SUCCESS) {
                ins = connection.getInputStream();
            } else {
                ins = connection.getErrorStream();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[4096];
            int len;
            while ((len = ins.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            ins.close();
            ResponseBody responseBody = new ResponseBody();
            responseBody.setHttpCode(code);
            responseBody.setData(baos.toByteArray());
            return responseBody;
        } catch (Exception e) {
            return null;
        }
    }

    public static ResponseBody sendGet(String url, Map<String, String> requestHead, Map<String, String> requestBody) {
        try {
            if (requestBody != null && requestBody.size() > 0) {
                StringBuilder param = new StringBuilder("?");
                for (String bodyKey : requestBody.keySet()) {
                    param.append(bodyKey).append("=").append(requestBody.get(bodyKey)).append("&");
                }
                url += param.substring(0, param.length() - 1);
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            String boundaryString = getBoundary();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            if (requestHead != null && requestHead.size() > 0) {
                for (String headKey : requestHead.keySet()) {
                    connection.setRequestProperty(headKey, requestHead.get(headKey));
                }
            }
            connection.connect();
            InputStream ins = null;
            int code = connection.getResponseCode();
            if (code == HTTP_SUCCESS) {
                ins = connection.getInputStream();
            } else {
                ins = connection.getErrorStream();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[4096];
            int len;
            while ((len = ins.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            ins.close();
            ResponseBody responseBody = new ResponseBody();
            responseBody.setHttpCode(code);
            responseBody.setData(baos.toByteArray());
            return responseBody;
        } catch (Exception e) {
            return null;
        }
    }
    public static String sendGet1(String url, Map<String, String> requestHead, Map<String, String> requestBody) {
        try {
            if (requestBody != null && requestBody.size() > 0) {
                StringBuilder param = new StringBuilder("?");
                for (String bodyKey : requestBody.keySet()) {
                    param.append(bodyKey).append("=").append(requestBody.get(bodyKey)).append("&");
                }
                url += param.substring(0, param.length() - 1);
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            String boundaryString = getBoundary();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            if (requestHead != null && requestHead.size() > 0) {
                for (String headKey : requestHead.keySet()) {
                    connection.setRequestProperty(headKey, requestHead.get(headKey));
                }
            }
            connection.connect();
            InputStream ins = null;
            int code = connection.getResponseCode();
            if (code == HTTP_SUCCESS) {
                ins = connection.getInputStream();
            } else {
                ins = connection.getErrorStream();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[4096];
            int len;
            while ((len = ins.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            ins.close();
            return baos.toString();
        } catch (Exception e) {
            return null;
        }
    }
    @Data
    public static class ResponseBody {
        private int httpCode;
        private byte[] data;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            sb.append(temp.charAt(random.nextInt(temp.length())));
        }
        return sb.toString();
    }

    private static String encode() throws Exception {
        return URLEncoder.encode(" ", "UTF-8");
    }

    public static String sendGetString(String url) throws Exception {
        String content = null;
        URLConnection urlConnection = new URL(url).openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder bs = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            content = bs.toString();
        }
        return content;
    }

    public static Map<Object, Object> getQiaHai(String path) {
        log.info("===================》getQiaHai ========== path：{}", path);
        String body = HttpRequest.get(path)
                .contentType("x-www-form-urlencoded")
                .execute()
                .body();
        log.info("===================》getQiaHai ========== path：{}", path + JSON.toJSONString(body));
        Map<Object, Object> map = JSON.parseObject(body, Map.class);
        return map;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("前海e站通政务服务中心", StandardCharsets.UTF_8.name());
        System.out.println(encode);
    }

    public static Map<Object, Object> postQiaHai(String path, JSONObject jsonObject) {
        log.info("===================》postQiaHai ========== path：{}", path);
        String body = HttpRequest.post(path)
                .contentType("x-www-form-urlencoded")
                .body(JSONUtil.toJsonStr(jsonObject))
                .execute()
                .body();
        log.info("===================》postQiaHai ========== path：{}", JSON.toJSONString(body));
        Map<Object, Object> map = JSON.parseObject(body, Map.class);
        return map;
    }

    public static Map<Object, Object> postQiFu(String path, String jsonObject) {
        log.info("===================》postQiaHai ========== path：{}", path);
        String body = HttpRequest.post(path)
                .contentType("application/json;charset=utf-8")
                .auth("bearerDispense")
                .body(JSONUtil.toJsonStr(jsonObject))
                .execute()
                .body();
        log.info("===================》postQiaHai ========== path：{}", JSON.toJSONString(body));
        Map<Object, Object> map = JSON.parseObject(body, Map.class);
        return map;
    }


}
