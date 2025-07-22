package org.api.springbootapiumlcase.resources.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {
    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }


    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}