package com.screw;

import java.text.MessageFormat;

/**
 * 领域的异常信息也是业务规则的一部分，有一些异常信息要提示给用户，有一些异常仅提示给开发人员用于错误定位。
 */
public class BusinessException extends RuntimeException {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public static void throwException(int errorCode, String message, Object ... arguments) {
        if (null != arguments && arguments.length > 0) {
            message = MessageFormat.format(message, arguments);
        }

        BusinessException exception = new BusinessException(message);
        exception.setErrorCode(errorCode);

        throw exception;
    }
}
