package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

/**
 * DriverManager — Appium driver factory for Android and iOS
 * Reads capabilities from config properties files
 */
public class DriverManager {

    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";

    public static AppiumDriver createDriver(String platform) throws IOException {
        if (platform.equalsIgnoreCase("android")) {
            return createAndroidDriver();
        } else if (platform.equalsIgnoreCase("ios")) {
            return createIOSDriver();
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    private static AndroidDriver createAndroidDriver() throws IOException {
        Properties props = loadConfig("android-config.properties");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion(props.getProperty("platform.version", "13.0"));
        options.setDeviceName(props.getProperty("device.name", "Android Emulator"));
        options.setApp(props.getProperty("app.path"));
        options.setAppPackage(props.getProperty("app.package"));
        options.setAppActivity(props.getProperty("app.activity"));
        options.setAutomationName("UIAutomator2");
        options.setNoReset(false);
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        return new AndroidDriver(new URL(APPIUM_SERVER), options);
    }

    private static IOSDriver createIOSDriver() throws IOException {
        Properties props = loadConfig("ios-config.properties");

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setPlatformVersion(props.getProperty("platform.version", "16.0"));
        options.setDeviceName(props.getProperty("device.name", "iPhone 14"));
        options.setApp(props.getProperty("app.path"));
        options.setBundleId(props.getProperty("bundle.id"));
        options.setAutomationName("XCUITest");
        options.setNoReset(false);
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        return new IOSDriver(new URL(APPIUM_SERVER), options);
    }

    private static Properties loadConfig(String fileName) throws IOException {
        Properties props = new Properties();
        String configPath = "src/test/resources/config/" + fileName;
        props.load(new FileInputStream(configPath));
        return props;
    }
}
