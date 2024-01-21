package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.SeleniumUtils;

public class LoginTest extends SeleniumUtils {

    private LoginPage loginPage;

    @BeforeClass
    public void setUpTest() {
        setUp();
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1)
    public void loginTest() {
        getDriver().get(getBaseUrl());

        // Use credentials from the config file
        String username = getUsername();
        String password = getPassword();

        // Perform login using page objects and generic functions
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Add assertions or verifications as needed
    }
}
