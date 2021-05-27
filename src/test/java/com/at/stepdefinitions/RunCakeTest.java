package com.at.stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","de.monochromata.cucumber.report.PrettyReports:target/cucumber",
        "html:target/cucumber-reports/cucumber",
        "json:target/cucumber-reports/cucumber.json"},
        features = {"src/test/resources/"},
        glue = {"com.at.stepdefinitions"},
        tags = "not @Deprecated",
        monochrome = false)
public class RunCakeTest {
}
