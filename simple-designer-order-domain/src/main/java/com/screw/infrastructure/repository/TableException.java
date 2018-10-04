package com.screw.infrastructure.repository;

import java.text.MessageFormat;

/**
 * 基础设施层的异常一般不提示给用户。
 */
public class TableException extends RuntimeException {
    private int errorCode = 9000;

    public int getErrorCode( ) {
        return errorCode;
    }

    public TableException() {
        super();
    }

    public TableException(String message) {
        super(message);
    }

    public TableException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableException(Throwable cause) {
        super(cause);
    }

    public static TableException throwTableException(String table, TableOperation operation) {
        String error = MessageFormat.format("Faild to do action '{0}' on table {1}.", operation, table);
        throw new TableException(error);
    }
}
