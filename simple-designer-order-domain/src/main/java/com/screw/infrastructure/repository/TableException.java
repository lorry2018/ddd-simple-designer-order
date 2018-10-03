package com.screw.infrastructure.repository;

public class TableException extends RuntimeException {
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
        String error = "Faild to do action '" + operation + "' on table " + table + ".";
        throw new TableException(error);
    }
}
