package pages.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * LoginPageIOS — iOS page object for login screen
 * Uses XCUITest locators: accessibility id, class chain, ios predicate
 */
public class LoginPageIOS {

    private final IOSDriver driver;
    private final WebDriverWait wait;

    // iOS locators using XCUITest
    @iOSXCUITFindBy(accessibility = "username_field")
    private WebElement usernameField;

    @iOSXCUITFindBy(accessibility = "password_field")
    private WebElement passwordField;

    @iOSXCUITFindBy(accessibility = "login_button")
    private WebElement loginButton;

    // iOS Predicate String locator
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name == 'error_label'")
    private WebElement errorMessage;

    // Class Chain locator
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name == 'Dashboard'`]")
    private WebElement dashboardTitle;

    public LoginPageIOS(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void tapLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        tapLoginButton();
    }

    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(dashboardTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}
