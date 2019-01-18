package cn.jackmore.core.coreException;

/**
 * @ClassName : BusinessException
 * @Description: 业务异常
 */
public class BusinessException extends BasicException {

    public BusinessException() {
        this(ExceptionEnum.业务异常.getMessage());
    }

    public BusinessException(String message) {
        super(message, ExceptionEnum.业务异常.getCode());
    }

}
