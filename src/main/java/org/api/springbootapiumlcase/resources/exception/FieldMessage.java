package org.api.springbootapiumlcase.resources.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class FieldMessage implements Serializable {
    private final String fieldName;
    private final String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}