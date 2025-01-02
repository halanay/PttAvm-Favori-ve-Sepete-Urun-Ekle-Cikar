package com.step_definitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    private static ExtentReports extentReport;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Before(order = 0)
    public void setup(Scenario scenario) {
        if (extentReport == null) {
            extentReport = new ExtentReports();
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = System.getProperty("user.dir") + "/target/ExtentReports/extentReport_" + tarih + ".html";


            extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
            extentReport.attachReporter(extentHtmlReporter);

            extentReport.setSystemInfo("Browser", "Chrome");
            extentReport.setSystemInfo("Tester", "Hamdi");
            extentHtmlReporter.config().setDocumentTitle("Extent Rapor");
            extentHtmlReporter.config().setReportName("PttAvm - Smoke Test Raporu");
        }

        ExtentTest test = extentReport.createTest(scenario.getName());
        extentTest.set(test);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        ExtentTest test = extentTest.get();

        if (scenario.isFailed()) {
            test.fail("Senaryo başarısız oldu: " + scenario.getName());
        } else {
            test.pass("Senaryo başarılı: " + scenario.getName());
        }

        extentReport.flush();

    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
