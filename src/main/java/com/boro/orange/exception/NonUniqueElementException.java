package com.boro.orange.exception;

/**
 * Created by petroborovets on 4/8/15.
 */
public class NonUniqueElementException extends Exception {
    private static final long serialVersionUID = -5912632877220512104L;

    public NonUniqueElementException() {
    }

    public NonUniqueElementException(String messsage) {
        super(messsage);
    }
}
