package com.autotests.ui.lib;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class ExtentManager {

    private static ExtentReports extent;
    public static String reportFilePath = "";

    public static ExtentReports createInstance(String fileName) {
        System.out.println(System.getProperty("os.name"));

        if(System.getProperty("os.name").contains("Mac")) {
            reportFilePath = System.getProperty("user.dir") + "/src/test/resources/reports/";
        } else {
            reportFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\";
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePath + fileName);
        System.out.println("Report being written to: " + reportFilePath);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    public static String screenshotName;

    /*
     Screenshot of failure point will be added to html report in case of failures
     */
    public static void captureScreenshot() {
        File scrFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        try {
            FileUtils.copyFile(scrFile, new File(reportFilePath + screenshotName));
        } catch (IOException e) {
            Logger.getLogger(ExtentManager.class).info("Screenshot generation failed");
        }
    }
}
