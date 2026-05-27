package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // ✅ Start of execution
    public void onStart(ITestContext testContext) {


        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Report configuration
        sparkReporter.config().setDocumentTitle("OpencartAutomationProject");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System info
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        String os= testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating system", os);
        
        String browser= testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating system", browser);
        
        List<String>includeGroups = (List<String>) testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includeGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includeGroups.toString());
        }
    }

    // ✅ Test Pass
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test Passed");
    }

    // ❌ Test Fail
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
        try {
        	String imPath = new BaseClass().captureScreen(result.getName());
        	test.addScreenCaptureFromPath(imPath);
        }catch(IOException e1)
        {
        	e1.printStackTrace();
        }
    }

    // ⚠️ Test Skipped
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable());
    }

    // 🔚 End of execution
    public void onFinish(ITestContext context) {
        extent.flush();
        //To open report automatically
        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);
        try {
        	Desktop.getDesktop().browse(extentReport.toURI());
        }catch(IOException e)
        {
        	e.printStackTrace();
        }
    }
}
