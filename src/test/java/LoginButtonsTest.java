import api.CreateUserViaApi;
import api.DeleteUserViaApi;
import api.data.UserDataRequest;
import browser.Browser;
import browser.BrowserFactory;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.ForgotPassword;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static config.Config.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginButtonsTest {

    private WebDriver driver;
    private final Browser browser = new BrowserFactory().prepareBrowserNamed(Config.getBrowser());
    private String accessToken;
    private LoginPage loginPage;

    @Before
    public void setUp() {

        // создадим пользователя через API

        UserDataRequest createUserRequest = new UserDataRequest(getEmail(), getUserPassword(), getUserName());
        CreateUserViaApi createUserViaApi = new CreateUserViaApi(createUserRequest);
        accessToken = createUserViaApi.fetchAccessToken();

        browser.driverManagerSetup();
        driver = browser.getNewDriver();

    }

    @Test
    @DisplayName("Test login user via Main Page. Login button")
    public void loginUserViaMainPageLoginButtonTest() {

        driver.get(getBaseURI());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        String accessToken = new LoginPage(driver).loginProcess();

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via Main Page. Account button")
    public void loginUserViaMainPageAccountButtonTest() {

        driver.get(getBaseURI());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        String accessToken = new LoginPage(driver).loginProcess();

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via Registration Page")
    public void loginUserViaRegistrationPageLoginButtonTest() {

        driver.get(String.format("%s%s", getBaseURI(), getRegisterPath()));
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();

        String accessToken = new LoginPage(driver).loginProcess();

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via ForgotPassword Page")
    public void loginUserViaForgotPasswordPageLoginButtonTest() {

        driver.get(String.format("%s%s", getBaseURI(), getForgotPasswordPath()));
        ForgotPassword forgotPassword = new ForgotPassword(driver);
        forgotPassword.clickLoginButton();

        String accessToken = new LoginPage(driver).loginProcess();

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @After
    public void tearDown() {
        new DeleteUserViaApi(accessToken).deleteUser();
        driver.quit();
    }
}
