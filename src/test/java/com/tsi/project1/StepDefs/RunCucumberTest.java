package com.tsi.project1.StepDefs;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/tsi/project1/features",
        plugin = {"pretty"}

)
public class RunCucumberTest {
}
