package com.eternum.book.entity.exception;

import lombok.Getter;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Getter
public class NotFoundException extends RuntimeException {

    private final String errorCode;
    private final String messageResourceCode;

    public NotFoundException(final String message, final String errorCode, final String messageResourceCode) {

        super(message);
        this.errorCode = errorCode;
        this.messageResourceCode = messageResourceCode;
    }

    public static NotFoundException create(final String message) {

        return new NotFoundException(message, EMPTY, message);
    }
}
