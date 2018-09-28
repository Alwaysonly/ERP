package com.huige.erp.common.exception;

/**
 * @Author Z.xichao
 * @Create 2018-9-10
 * @Comments
 */
public abstract class BaseAppException extends RuntimeException {

    private static final long serialVersionUID = -396201416508565280L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BaseAppException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BaseAppException(String message) {
        super(message);
    }
}
