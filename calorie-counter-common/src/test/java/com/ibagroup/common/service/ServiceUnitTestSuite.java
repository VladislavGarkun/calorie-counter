package com.ibagroup.common.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ConfirmationServiceUnitTest.class,
        MealServiceUnitTest.class,
        ProductServiceUnitTest.class
})
public class ServiceUnitTestSuite {



}
