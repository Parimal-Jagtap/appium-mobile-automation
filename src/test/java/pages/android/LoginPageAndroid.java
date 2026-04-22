package pages.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * LoginPageAndroid — Android page object for login screen
 * Uses UIAutomator2 locators: resource-id, accessibility-id, xpath
 */
public class LoginPageAndroid {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Android locators using UIAutomator2
    @AndroidFindBy(accessibility = "username_field")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "password_field")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "login_button")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.wallet.app:id/error_message")
    private WebElement errorMessage;

    @AndroidFindBy(id = "com.wallet.app:id/dashboard_title")
    private WebElement dashboardTitle;

    public LoginPageAndroid(AndroidDriver driver) {
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
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
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
