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
 * WalletPageAndroid — Android page object for wallet screen
 * Covers: balance view, add money, transaction history
 */
public class WalletPageAndroid {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    @AndroidFindBy(id = "com.wallet.app:id/wallet_balance")
    private WebElement walletBalance;

    @AndroidFindBy(accessibility = "add_money_button")
    private WebElement addMoneyButton;

    @AndroidFindBy(id = "com.wallet.app:id/amount_input")
    private WebElement amountInput;

    @AndroidFindBy(accessibility = "confirm_button")
    private WebElement confirmButton;

    @AndroidFindBy(id = "com.wallet.app:id/success_message")
    private WebElement successMessage;

    @AndroidFindBy(id = "com.wallet.app:id/transaction_history")
    private WebElement transactionHistory;

    public WalletPageAndroid(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getWalletBalance() {
        return wait.until(ExpectedConditions.visibilityOf(walletBalance)).getText();
    }

    public void tapAddMoney() {
        wait.until(ExpectedConditions.elementToBeClickable(addMoneyButton)).click();
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(amountInput));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void confirmTransaction() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isTransactionSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addMoneyToWallet(String amount) {
        tapAddMoney();
        enterAmount(amount);
        confirmTransaction();
    }
}
