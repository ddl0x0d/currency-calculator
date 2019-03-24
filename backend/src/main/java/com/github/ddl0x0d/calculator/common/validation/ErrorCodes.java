package com.github.ddl0x0d.calculator.common.validation;

public abstract class ErrorCodes {

    /*---------------- Bean Validation error codes ----------------*/

    public static final String CURRENCY_INVALID = "{currency.invalid}";
    public static final String CURRENCY_UNSUPPORTED = "{currency.unsupported}";
    public static final String CURRENCY_SAME = "{currency.same}";
    public static final String FEE_DUPLICATE = "{fee.duplicate}";

    /*---------------- Custom validation error codes ----------------*/

    public static final String CURRENCY_PROVIDER = "currency.provider";
    public static final String UNEXPECTED_ERROR = "unexpected.error";

}
