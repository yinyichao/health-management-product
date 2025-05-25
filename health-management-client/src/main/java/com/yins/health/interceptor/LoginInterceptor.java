package com.yins.health.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.yins.health.common.ServiceException;
import com.yins.health.conf.RedisKey;
import com.yins.health.conf.RemoteServiceConfig;
import com.yins.health.dto.AccountDto;
import com.yins.health.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RemoteServiceConfig remoteServiceConfig;

    public static ThreadLocal<AccountDto> threadLocal = new ThreadLocal<>();
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisKey redisKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理options请求
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        try {
            // 1. 获取 Token 和 tokenKey
            String token = request.getHeader("Authorization");

            // 2. 参数校验
            if (StringUtils.isEmpty(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                throw new ServiceException("登录信息无效");
            }

            // 检查是否为特殊API密钥
            if (StrUtil.equals(token,"Bearer " + remoteServiceConfig.getAppKey())) {
                log.info("使用特殊API密钥访问，跳过验证");
                // 设置预定义的用户信息到ThreadLocal
                AccountDto accountDTO = AccountDto.builder()
                    .id(Long.valueOf(remoteServiceConfig.getSpecialId()) )
                    .username("boss")
                    .build();
                threadLocal.set(accountDTO);
                return true;
            }

            // 5. 解析 Token 并设置用户信息
            JWT jwt = JWTUtil.parseToken(token);
            Long userId = Long.parseLong(jwt.getPayload("accountId").toString());
            // 3. 验证 Token 有效性
            //String rawRedisToken = stringRedisTemplate.opsForValue().get(RedisKeyEnum.LOGIN_TOKEN.getPrefix()+tokenKey);
            String tokenKey = redisKey.token_key_pre + userId;
            String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
            if (redisToken == null || !redisToken.equals(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                throw new ServiceException("登录信息无效");
            }
            // 4. Token 续期
            Long expireTime = redisUtil.getExpire(tokenKey);
            if (expireTime < 60 * 30) {
                redisUtil.expire(tokenKey, 3, TimeUnit.HOURS);
                log.info("Token 续期成功，新的过期时间 3 小时");
            }
            // 6. 设置用户信息到上下文

            String username = (String) jwt.getPayload("username");
            AccountDto accountDTO = AccountDto.builder().id(userId).username(username).build();
            threadLocal.set(accountDTO);
            return true;
        } catch (Exception e) {
            log.error("登录验证失败", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new ServiceException("登录验证失败：" + e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理threadLocal，避免内存泄漏
        threadLocal.remove();
    }
}