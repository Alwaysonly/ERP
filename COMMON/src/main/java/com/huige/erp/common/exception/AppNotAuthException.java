package com.huige.erp.common.exception;

/**
 * @Author Z.xichao
 * @Create 2018-9-10
 * @Comments
 */
public class AppNotAuthException extends BaseAppException {

    private static final long serialVersionUID = -1525891520595136642L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AppNotAuthException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AppNotAuthException(String message) {
        super(message);
    }
}
