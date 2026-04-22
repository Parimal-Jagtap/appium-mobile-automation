package base;

import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverManager;

import java.io.IOException;

/**
 * BaseTest — Common setup and teardown for all mobile tests
 * Handles: driver initialization, screenshot on failure, cleanup
 */
public class BaseTest {

    protected AppiumDriver driver;

    @BeforeMethod
    @Parameters({"platform"})
    public void setUp(@Optional("android") String platform) throws IOException {
        driver = DriverManager.createDriver(platform);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }
}
