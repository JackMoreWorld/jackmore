package cn.jackmore.core.coreException;

/**
 * @Description: 参数异常
 */
public class ParameterException extends BasicException {


    public ParameterException() {
        this(ExceptionEnum.参数异常.getMessage());
    }

    public ParameterException(String message) {
        super(message, ExceptionEnum.参数异常.getCode());
    }

}
