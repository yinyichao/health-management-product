package com.yins.health.util.ip;

import com.alibaba.fastjson.JSONObject;
import com.yins.health.util.StringUtils;
import com.yins.health.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @Author yinhuiqing
 * @description 获取地址信息类接口
 * @date 2023/3/24 15:24
 **/
public class AddressUtil {

    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询的第三方接口
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        if (ip == null) {
            return UNKNOWN;
        }

        String address = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        try {
            String rspStr = HttpUtils.get(IP_URL, "ip=" + ip + "&json=true");
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", e);
        }

        return address;
    }


}
