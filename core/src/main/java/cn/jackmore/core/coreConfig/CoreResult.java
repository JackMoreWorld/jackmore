package cn.jackmore.core.coreConfig;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class CoreResult<T> {

    private Integer code;
    private String message;
    private T data;


    public JSONObject toJSON() {
        JSONObject rpInfo = new JSONObject();
        rpInfo.put("code", getCode());
        rpInfo.put("Message", getMessage());
        rpInfo.put("Data", getData());
        return rpInfo;
    }

}
