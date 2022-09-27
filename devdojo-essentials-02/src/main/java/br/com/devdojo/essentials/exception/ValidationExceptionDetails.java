package br.com.devdojo.essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ValidationExceptionDetails extends ExceptionDetails{

    private final String fields;
    private final String fieldsMessage;

}
