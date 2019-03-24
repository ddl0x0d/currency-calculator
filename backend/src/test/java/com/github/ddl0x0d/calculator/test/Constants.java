package com.github.ddl0x0d.calculator.test;

import java.math.BigDecimal;

public abstract class Constants {

    public static final String EUR = "EUR";
    public static final String GBP = "GBP";
    public static final String[] CURRENCIES = {EUR, GBP};

    public static final String FROM = EUR;
    public static final String TO = GBP;

    public static final BigDecimal FEE = new BigDecimal("0.01");
    public static final BigDecimal RATE = new BigDecimal("0.85");
    public static final BigDecimal AMOUNT = new BigDecimal(100);
    public static final BigDecimal CONVERSION = new BigDecimal("0.84");

}
