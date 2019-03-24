package com.github.ddl0x0d.calculator.fee.validation;

import com.github.ddl0x0d.calculator.common.validation.ConvertibleChecks;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ConvertibleChecks.class, FeeChecks.class})
public interface FeeChecksOrder {

}
