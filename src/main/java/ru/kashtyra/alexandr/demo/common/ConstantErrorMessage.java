package ru.kashtyra.alexandr.demo.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantErrorMessage {

    public static final String NOT_EMPTY = "Field is not be empty";

    public static final String POSITIVE = "Value [${validatedValue}] is not be negative";

    public static final String NOT_NULL = "Value [${validatedValue}] must be set";

    public static final String NOT_BLANK = "The line should not be empty!";
}