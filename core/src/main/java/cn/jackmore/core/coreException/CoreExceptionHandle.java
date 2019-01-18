package cn.jackmore.core.coreException;

import cn.jackmore.core.coreConfig.CoreResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 接口异常统一处理
 *
 * @author JackMore
 */
@Slf4j
@RestControllerAdvice
public class CoreExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public String dealExceptions(Throwable et) {
        CoreResult rst = new CoreResult();
        try {
            if (log.isDebugEnabled()) {
                et.printStackTrace();
            }
            throw et;
        } catch (BasicException e) {
            rst.setMessage(e.getMessage());
            rst.setCode(e.getCode());
            rst.setData(null);
        } catch (Throwable e) {
            String msg = "系统异常，请稍后再试";
            if (StringUtils.isNotBlank(e.getMessage())) {
                msg = e.getMessage();
            }
            rst.setMessage(msg);
            rst.setCode(500);
            rst.setData(null);
            e.printStackTrace();
        }
        String json = JSONObject.toJSONString(rst.toJSON(), SerializerFeature.WriteMapNullValue);
        log.error("异常返回：{}\r\n{}", et.toString(), json);
        return json;
    }

}