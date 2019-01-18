package cn.jackmore.core.coreInterceptors;

import cn.jackmore.core.coreAnnotations.ValidGoogle;
import cn.jackmore.core.coreAnnotations.ValidToken;
import cn.jackmore.core.coreConfig.CoreConfiguration;
import cn.jackmore.core.coreConfig.CoreUser;
import cn.jackmore.core.coreException.AuthenticationException;
import cn.jackmore.core.coreException.BusinessException;
import cn.jackmore.core.coreUtils.AESUtil;
import cn.jackmore.core.coreUtils.CoreRedis;
import cn.jackmore.core.coreUtils.EncryptUtil;
import cn.jackmore.core.coreUtils.GoogleUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 处理所有标注的注解
 */
public class CoreInterceptor implements HandlerInterceptor {
    @Autowired
    private CoreRedis redis;

    private CoreConfiguration config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ValidToken validToken = method.getAnnotation(ValidToken.class);
        // 有 @ValidToken 注解，需要token认证
        if (validToken != null) {
            String token = request.getHeader("token");
            if (token == null || !redis.exists(config.getTokenPrefix() + token)) {
                throw new BusinessException("token已失效！");
            }
        }


        ValidGoogle googleAuth = method.getAnnotation(ValidGoogle.class);
        // @GoogleAuth 注解，需要谷歌认证
        if (googleAuth != null) {
            String token = request.getHeader("token");
            Boolean shouldValid = Boolean.parseBoolean(request.getHeader("shouldValid"));
            if (shouldValid) {
                Long googleCode = Long.valueOf(request.getHeader("googleCode"));
                String redisUser = redis.get(config.getTokenPrefix() + token);
                CoreUser userDto = JSONObject.parseObject(redisUser, CoreUser.class);
                String auth = userDto.getGoogleAuth();
                byte[] authKey = AESUtil.decrypt(EncryptUtil.toByteArray(auth));
                String privateKey = new String(authKey);
                boolean result = GoogleUtil.checkGoogleCode(googleCode, System.currentTimeMillis(), privateKey);
                if (!result) {
                    throw new AuthenticationException("谷歌验证码错误！:" + googleCode);
                }
            }

        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
