package com.huige.erp.common.exception;

/**
 * @Author Z.xichao
 * @Create 2018-9-10
 * @Comments
 */
public class AppParmMissException extends BaseAppException {

    private static final long serialVersionUID = 54669363336629102L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AppParmMissException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AppParmMissException(String message) {
        super(message);
    }
}
