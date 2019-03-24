package com.acme.calculator.fee.validation;

import com.acme.calculator.common.validation.ConvertibleChecks;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ConvertibleChecks.class, FeeChecks.class})
public interface FeeChecksOrder {

}
