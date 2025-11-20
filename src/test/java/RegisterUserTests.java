import browser.Browser;
import browser.BrowserFactory;
import api.DeleteUserViaApi;
import api.LoginUserViaApi;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

import static config.Config.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class RegisterUserTests {

    private WebDriver driver;
    private final Browser browser = new BrowserFactory().prepareBrowserNamed(Config.getBrowser());
    private String accessToken;


    @Before
    public void setUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();

    }

    @Test
    @DisplayName("Test register user. Browser: Chrome")
    public void createUserViaRegisterForm() {

        driver.get(String.format("%s%s", getBaseURI(), getRegisterPath()));

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillName(getUserName());
        registerPage.fillEmail(getEmail());
        registerPage.fillPassword(getUserPassword());
        registerPage.clickRegisterButton();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(new LoginPage(driver).getLoginHeader()));

        // авторизируемся через API
        LoginUserViaApi loginUserViaApi = new LoginUserViaApi(getEmail(), getUserPassword());
        accessToken = loginUserViaApi.fetchAccessToken();

        assertThat("Логин неуспешен", accessToken, notNullValue());

    }

    @After
    public void tearDown() {

        new DeleteUserViaApi(accessToken).deleteUser();
        driver.quit();

    }
}
