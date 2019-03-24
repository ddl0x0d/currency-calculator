package com.acme.calculator.fee.data;

import org.springframework.context.annotation.Import;

@Import({FeeRepositorySimple.class, FeeRepositoryRedis.class})
public class FeeRepositorySimpleTest extends AbstractFeeRepositoryTest {

}
