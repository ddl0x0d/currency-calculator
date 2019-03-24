package com.github.ddl0x0d.calculator.fee.data;

import org.springframework.context.annotation.Import;

@Import({FeeRepositorySimple.class, FeeRepositoryRedis.class})
public class FeeRepositorySimpleTest extends AbstractFeeRepositoryTest {

}
