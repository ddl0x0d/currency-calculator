package com.github.ddl0x0d.calculator.test.validation;

import static java.lang.String.format;

public abstract class ValidationMessages {

    public static String notNull() {
        return "must not be null";
    }

    public static String notEmpty() {
        return "must not be empty";
    }

    public static String currencyInvalid(String value) {
        return format("Value '%s' is not a valid currency according to ISO 4217", value);
    }

    public static String currencyUnsupported(String value) {
        return format("Currency '%s' is not supported by currency provider", value);
    }

    public static String currencySame() {
        return "Currencies must be different";
    }

    public static String feeDuplicate(final String from, final String to) {
        return format("Fee for conversion from '%s' to '%s' already exists", from, to);
    }

    public static String currencyProvider(String message) {
        return format("Currency provider error: %s", message);
    }

    public static String unexpectedError(String error) {
        return format("Unexpected error has occurred: %s", error);
    }
}
