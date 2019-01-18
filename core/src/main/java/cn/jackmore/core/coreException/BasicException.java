package cn.jackmore.core.coreException;

import lombok.Data;

/**
 * @ClassName : BasicException
 * @Description: 基础异常，所有的异常实现均要继承该异常
 */
@Data
public class BasicException extends RuntimeException {

    private Integer code;
    private String message;
    private String data;


    public BasicException(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BasicException(String message, String errorCode) {
        super(message);
    }


}
