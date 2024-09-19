package org.test.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/amazon.feature",
glue="org/test/glue/definition",
plugin={"pretty","junit:target/jUnitReports/report.xml",
		"json:target/JSONReports/reports.json",
		"html:target/report.html"},
monochrome = true)
public class TestRunner {

}
