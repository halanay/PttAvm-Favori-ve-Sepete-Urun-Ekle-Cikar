package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                //"json:target/report.json",

                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                //"html:target/cucumber-default-report",
              //  "html:target/default-html-reports",
                "rerun:target/rerun.txt",
                //"me.jvt.cucumber.report.PrettyReports:target/cucumber"
                },
        /*plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/json-report/cucumber.json",
                "junit:target/resources/feature"
        },*/
        //format = {"json:target/json-report/cucumber.json","html:target/cucumber-reports.html",},
        features = "src/test/resources/features",
        glue = "com/step_definitions",
        dryRun = false,
        tags = "@wip"
)
public class CukesRunner {
}
