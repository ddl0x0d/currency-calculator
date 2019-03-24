package com.acme.calculator.common.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ConvertibleChecks.class})
public interface ConvertibleChecksOrder {

}
