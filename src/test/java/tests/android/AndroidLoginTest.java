package tests.android;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.android.LoginPageAndroid;
import pages.android.WalletPageAndroid;
import utils.TestDataReader;

/**
 * AndroidLoginTest — Test cases for Android login and wallet
 * Covers: valid login, invalid login, wallet balance, add money
 */
public class AndroidLoginTest extends BaseTest {

    @Test(groups = "smoke", description = "Valid login on Android")
    public void testValidLoginAndroid() {
        LoginPageAndroid loginPage = new LoginPageAndroid((AndroidDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        Assert.assertTrue(loginPage.isDashboardDisplayed(),
                "Dashboard not displayed after valid Android login");
    }

    @Test(groups = "regression", description = "Invalid login shows error on Android")
    public void testInvalidLoginAndroid() {
        LoginPageAndroid loginPage = new LoginPageAndroid((AndroidDriver) driver);
        loginPage.login(
                TestDataReader.getString("invalidUser.username"),
                TestDataReader.getString("invalidUser.password")
        );
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid username or password",
                "Error message mismatch on Android");
    }

    @Test(groups = "smoke", description = "View wallet balance on Android",
            dependsOnMethods = "testValidLoginAndroid")
    public void testViewWalletBalanceAndroid() {
        LoginPageAndroid loginPage = new LoginPageAndroid((AndroidDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        WalletPageAndroid walletPage = new WalletPageAndroid((AndroidDriver) driver);
        String balance = walletPage.getWalletBalance();
        Assert.assertNotNull(balance, "Wallet balance should not be null");
        Assert.assertFalse(balance.isEmpty(), "Wallet balance should not be empty");
    }

    @Test(groups = "regression", description = "Add money to wallet on Android")
    public void testAddMoneyToWalletAndroid() {
        LoginPageAndroid loginPage = new LoginPageAndroid((AndroidDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        WalletPageAndroid walletPage = new WalletPageAndroid((AndroidDriver) driver);
        walletPage.addMoneyToWallet(TestDataReader.getString("wallet.addAmount"));
        Assert.assertTrue(walletPage.isTransactionSuccessful(),
                "Add money transaction failed on Android");
    }
}
