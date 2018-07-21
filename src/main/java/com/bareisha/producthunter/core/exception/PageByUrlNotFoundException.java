package com.bareisha.producthunter.core.exception;

public class PageByUrlNotFoundException extends RuntimeException {
    public PageByUrlNotFoundException() {
        super();
    }

    public PageByUrlNotFoundException(String message) {
        super(message);
    }

    public PageByUrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageByUrlNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PageByUrlNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
