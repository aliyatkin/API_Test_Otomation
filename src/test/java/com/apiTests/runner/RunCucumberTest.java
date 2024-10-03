package com.apiTests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/apiTests/serviceSteps/serviceSteps",                     // Packet name
        features = "src/test/resources/features/user-controller",                           // .feature files location
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}       // Allure plugin
)
public class RunCucumberTest {
} 