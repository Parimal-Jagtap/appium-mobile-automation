package tests.ios;

import base.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ios.LoginPageIOS;
import pages.ios.WalletPageIOS;
import utils.TestDataReader;

/**
 * IOSLoginTest — Test cases for iOS login and wallet
 * Covers: valid login, invalid login, wallet balance, add money
 */
public class IOSLoginTest extends BaseTest {

    @Test(groups = "smoke", description = "Valid login on iOS")
    public void testValidLoginIOS() {
        LoginPageIOS loginPage = new LoginPageIOS((IOSDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        Assert.assertTrue(loginPage.isDashboardDisplayed(),
                "Dashboard not displayed after valid iOS login");
    }

    @Test(groups = "regression", description = "Invalid login shows error on iOS")
    public void testInvalidLoginIOS() {
        LoginPageIOS loginPage = new LoginPageIOS((IOSDriver) driver);
        loginPage.login(
                TestDataReader.getString("invalidUser.username"),
                TestDataReader.getString("invalidUser.password")
        );
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid username or password",
                "Error message mismatch on iOS");
    }

    @Test(groups = "smoke", description = "View wallet balance on iOS",
            dependsOnMethods = "testValidLoginIOS")
    public void testViewWalletBalanceIOS() {
        LoginPageIOS loginPage = new LoginPageIOS((IOSDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        WalletPageIOS walletPage = new WalletPageIOS((IOSDriver) driver);
        String balance = walletPage.getWalletBalance();
        Assert.assertNotNull(balance, "Wallet balance should not be null on iOS");
        Assert.assertFalse(balance.isEmpty(), "Wallet balance should not be empty on iOS");
    }

    @Test(groups = "regression", description = "Add money to wallet on iOS")
    public void testAddMoneyToWalletIOS() {
        LoginPageIOS loginPage = new LoginPageIOS((IOSDriver) driver);
        loginPage.login(
                TestDataReader.getString("validUser.username"),
                TestDataReader.getString("validUser.password")
        );
        WalletPageIOS walletPage = new WalletPageIOS((IOSDriver) driver);
        walletPage.addMoneyToWallet(TestDataReader.getString("wallet.addAmount"));
        Assert.assertTrue(walletPage.isTransactionSuccessful(),
                "Add money transaction failed on iOS");
    }
}
