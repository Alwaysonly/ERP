package com.huige.erp.common.exception;

/**
 * @Author Z.xichao
 * @Create 2018-9-10
 * @Comments
 */
public class AppCommonException extends BaseAppException {
    private static final long serialVersionUID = -6390685516904945479L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AppCommonException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AppCommonException(String message) {
        super(message);
    }
}
