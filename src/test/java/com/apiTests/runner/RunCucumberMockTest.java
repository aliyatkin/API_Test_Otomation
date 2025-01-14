package com.apiTests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/apiTests/serviceSteps/mockTestSteps",                          // Packet name
        features = {"src/test/resources/featuresForMock/user-controller-Mock"},    // .feature files location
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}              // Allure plugin
)
public class
RunCucumberMockTest {
}
