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
 * WalletPageIOS — iOS page object for wallet screen
 * Covers: balance view, add money, transaction history
 */
public class WalletPageIOS {

    private final IOSDriver driver;
    private final WebDriverWait wait;

    @iOSXCUITFindBy(accessibility = "wallet_balance_label")
    private WebElement walletBalance;

    @iOSXCUITFindBy(accessibility = "add_money_button")
    private WebElement addMoneyButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name == 'amount_input'`]")
    private WebElement amountInput;

    @iOSXCUITFindBy(accessibility = "confirm_button")
    private WebElement confirmButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name == 'success_label'")
    private WebElement successMessage;

    public WalletPageIOS(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getWalletBalance() {
        return wait.until(ExpectedConditions.visibilityOf(walletBalance)).getText();
    }

    public void addMoneyToWallet(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(addMoneyButton)).click();
        wait.until(ExpectedConditions.visibilityOf(amountInput));
        amountInput.clear();
        amountInput.sendKeys(amount);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isTransactionSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
