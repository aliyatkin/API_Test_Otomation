package com.apiTests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/apiTests/serviceSteps/serviceTestSteps",                         // Packet name
        features = {"src/test/resources/features"},                                  // Feature files
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}                // Allure plugin
)
public class RunCucumberTest {
} 