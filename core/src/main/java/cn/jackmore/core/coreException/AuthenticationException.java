package cn.jackmore.core.coreException;


/**
 * 安全认证异常
 */
public class AuthenticationException extends BasicException {


    public AuthenticationException() {
        this(ExceptionEnum.验证异常.getMessage());
    }

    public AuthenticationException(String message) {
        super(message, ExceptionEnum.验证异常.getCode());
    }

}
